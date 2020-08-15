package com.mecanica.controller.avaliacao;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.mecanica.application.applicationServices.cliente.ClienteValidations;
import com.mecanica.application.applicationServices.funcionario.FuncionarioValidations;
import com.mecanica.application.applicationServices.mecanico.MecanicoValidations;
import com.mecanica.application.applicationServices.orcamento.OrcamentoValidations;
import com.mecanica.application.applicationServices.produto.ProdutoValidations;
import com.mecanica.application.applicationServices.servico.ServicoValidations;
import com.mecanica.application.applicationServices.veiculo.VeiculoValidations;
import com.mecanica.application.dto.avaliacao.AvaliacaoMecanicoDto;
import com.mecanica.application.dto.avaliacao.PedidoAvaliacaoDto;
import com.mecanica.application.dto.avaliacao.SemConsertoDto;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.cliente.ClienteHistoricoRetorno;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.servico.ItemServico;
import com.mecanica.domain.entities.servico.ServicoOrcamento;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.services.cliente.ClienteHistoricoRetornoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/avaliacao")
public class AvaliacaoController extends BaseController {

    private final OrcamentoValidations _serviceOrcamento;
    private final ProdutoValidations _serviceProduto;
    private final ServicoValidations _serviceServico;

    private final FuncionarioValidations _serviceFuncionario;
    private final MecanicoValidations _serviceMecanico;
    private final ClienteValidations _serviceCliente;
    private final VeiculoValidations _serviceVeiculo;
    private final ClienteHistoricoRetornoService _clienteHistoricoRetornoService;

    @Autowired
    public AvaliacaoController(
        ProdutoValidations serviceProduto,
        ServicoValidations serviceServico,
        OrcamentoValidations orcamentoComum, ClienteValidations serviceCliente,
            VeiculoValidations serviceVeiculo, FuncionarioValidations serviceFuncionario,
            MecanicoValidations serviceMecanico, ClienteHistoricoRetornoService clienteHistoricoRetornoService) {
        _serviceOrcamento = orcamentoComum;
        _serviceProduto = serviceProduto;
        _serviceServico = serviceServico;
        _serviceCliente = serviceCliente;
        _serviceVeiculo = serviceVeiculo;
        _serviceFuncionario = serviceFuncionario;
        _serviceMecanico = serviceMecanico;
        _clienteHistoricoRetornoService = clienteHistoricoRetornoService;
    }

    @GetMapping("list")
    @ApiOperation(value = "Lista models mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<Orcamento>> list(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") @RequestParam(name = "page") int page) {

        Page<Orcamento> list = this._serviceOrcamento.findAllBySituacaoEquals(EnumSituacaoOrcamento.Aguardando, page);

        return ResponseEntity.ok(list);
    }

    @PostMapping("pedidoAvaliacao")
    @ApiOperation(value = "Faz um novo pedido de orçamento com avaliação do mecânico")
    public ResponseEntity<Orcamento> pedidoAvaliacao(
        @RequestBody @Valid PedidoAvaliacaoDto pedidoAvaliacao
    ) {
        Funcionario atendente = _serviceFuncionario.findValidExistsByCpf(pedidoAvaliacao.getFuncionarioCpf());
        Cliente cliente = _serviceCliente.findValidExistsById(pedidoAvaliacao.getClienteId());
        Veiculo veiculo = _serviceVeiculo.findValidExistsByRenavam(pedidoAvaliacao.getVeiculoRenavam());
        Orcamento entity = this._serviceOrcamento
        .criarPedidoAvaliacao(atendente, cliente, veiculo, pedidoAvaliacao.getDescricaoProblema());

        _serviceOrcamento.save(entity);

        return ResponseEntity.ok(entity);
    }

    /**
     * Valida Se existe mecânico Configura Avaliação feito pelo mecanico
     * 
     * @param mecanicoCpf
     * @param identificacao
     * @param avaliacaoMecanico
     * @return
     */
    @PostMapping("incluir/avaliacao")
    @ApiOperation(value = "Permite um mecânico incluir a avaliação e Serviços ou indicar que não há conserto")
    public ResponseEntity<Orcamento> incluirAvaliacao(@RequestBody @Valid AvaliacaoMecanicoDto avaliacaoMecanico) {

        Mecanico mecanico = _serviceMecanico.findValidExistsByCpf(avaliacaoMecanico.getMecanicoCpf());
        Orcamento entity = this._serviceOrcamento.findValidExistsByIdentificacao(avaliacaoMecanico.getIdentificacao());
        
        List<ServicoOrcamento> servicosServicoOrcamento = _serviceServico.findAllValidExistsByFilter(avaliacaoMecanico.getServicos());
        List<ItemServico> servicosItemServico = _serviceProduto.findAllValidExistsByFilter(avaliacaoMecanico.getItensServico());

        Avaliacao avaliacao = avaliacaoMecanico.getAvaliacao();
        int dias = avaliacaoMecanico.getDias();
        LocalDate dataPrevisaoInicio = avaliacaoMecanico.getDataPrevisaoInicio();

        entity = this._serviceOrcamento.configurarAvaliacao(entity, avaliacao, mecanico, dias, dataPrevisaoInicio);
        entity = this._serviceOrcamento.configurarServicos(entity, servicosServicoOrcamento);
        entity = this._serviceOrcamento.configurarItemServico(entity, servicosItemServico);
        entity = this._serviceOrcamento.configurarSituacaoOrcamento(entity);

        _serviceOrcamento.save(entity);        

        return ResponseEntity.ok(entity);
    }

    @PostMapping("semconcerto")
    @ApiOperation(value = "O Avaliador indica que o veículo não tem conserto")
    public ResponseEntity<Orcamento> semconcerto(@RequestBody @Valid SemConsertoDto semConserto) {
        Mecanico mecanico = _serviceMecanico.findValidExistsByCpf(semConserto.getMecanicoCpf());
        
        Orcamento entity = this._serviceOrcamento
        .veiculoSemConcerto(semConserto.getIdentificacao(), mecanico);

        _serviceOrcamento.update(entity);

        return ResponseEntity.ok(entity);
    }

    /**
     * Valida Se existe mecânico Configura Avaliação feito pelo mecanico
     * 
     * @param mecanicoCpf
     * @param identificacao
     * @param avaliacaoMecanico
     * @return
     */
    @PostMapping("informar/cliente")
    @ApiOperation(value = "Registra as comunicação com o cliente")
    public ResponseEntity<ClienteHistoricoRetorno> informarCliente(
            @ApiParam(example = "xxxxxxxxxx", value = "cpf do funcionário cadastrado") @RequestParam(name = "funcionarioCpf") String funcionarioCpf,
            @RequestBody @Valid ClienteHistoricoRetorno clienteHistoricoRetorno) {

        Funcionario atendente = _serviceFuncionario.findValidExistsByCpf(funcionarioCpf);
        Orcamento orcamento = this._serviceOrcamento.findValidExistsByIdentificacao(clienteHistoricoRetorno.getIdentificacao());

        ClienteHistoricoRetorno entity = this._clienteHistoricoRetornoService.informarCliente(atendente, orcamento,
                clienteHistoricoRetorno);

        _clienteHistoricoRetornoService.save(entity);

        return ResponseEntity.ok(entity);
    }
}