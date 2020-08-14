package com.mecanica.controller.avaliacao;

import java.util.List;

import javax.validation.Valid;

import com.mecanica.application.dto.avaliacao.AvaliacaoMecanicoDto;
import com.mecanica.application.validation.cliente.ClienteValidations;
import com.mecanica.application.validation.funcionario.FuncionarioValidations;
import com.mecanica.application.validation.mecanico.MecanicoValidations;
import com.mecanica.application.validation.orcamento.OrcamentoValidations;
import com.mecanica.application.validation.veiculo.VeiculoValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.cliente.ClienteHistoricoRetorno;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
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
    private final FuncionarioValidations _serviceFuncionario;
    private final MecanicoValidations _serviceMecanico;
    private final ClienteValidations _serviceCliente;
    private final VeiculoValidations _serviceVeiculo;
    private final ClienteHistoricoRetornoService _clienteHistoricoRetornoService;

    @Autowired
    public AvaliacaoController(OrcamentoValidations orcamentoComum, ClienteValidations serviceCliente,
            VeiculoValidations serviceVeiculo, FuncionarioValidations serviceFuncionario,
            MecanicoValidations serviceMecanico, ClienteHistoricoRetornoService clienteHistoricoRetornoService) {
        _serviceOrcamento = orcamentoComum;
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

        Page<Orcamento> list = this._serviceOrcamento.get_service().findAllSituacao(EnumSituacaoOrcamento.Aguardando, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("pedidoAvaliacao")
    @ApiOperation(value = "Faz um novo pedido de orçamento com avaliação do mecânico")
    public ResponseEntity<Orcamento> pedidoAvaliacao(
            @ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do funcionário cadastrado") @RequestParam(name = "funcionarioCpf") String funcionarioCpf,
            @ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do cliente cadastrado") @RequestParam(name = "clienteId") String clienteId,
            @ApiParam(example = "123", value = "número do renavam do veículo cadastrado") @RequestParam(name = "veiculoRenavam") String veiculoRenavam,
            @ApiParam(example = "Carro não está freando", value = "descrição das causas do problema relatados pelo cliente") @RequestParam(name = "causas") String causas) {
        Funcionario atendente = _serviceFuncionario.findValidExistsByCpf(funcionarioCpf);
        Cliente cliente = _serviceCliente.findValidExistsById(clienteId);
        Veiculo veiculo = _serviceVeiculo.findValidExistsByRenavam(veiculoRenavam);
        Orcamento entity = this._serviceOrcamento.get_service().criarPedidoAvaliacao(atendente, cliente, veiculo, causas);

        _serviceOrcamento.get_service().save(entity);

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
    public ResponseEntity<Orcamento> incluirAvaliacao(
            @ApiParam(example = "xxxxxxxxxx", value = "cpf do mecânico cadastrado") @RequestParam(name = "mecanicoCpf") String mecanicoCpf,
            @ApiParam(example = "Tafarel Rivelino Ronaldo dinho 123123", value = "Código de Identificação: default:  ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") @RequestParam(name = "identificacao") String identificacao,
            @RequestBody @Valid AvaliacaoMecanicoDto avaliacaoMecanico) {
        Mecanico mecanico = _serviceMecanico.findValidExistsByCpf(mecanicoCpf);
        Orcamento entity = this._serviceOrcamento.findValidExistsByIdentificacao(identificacao);
        
        Avaliacao avaliacao = avaliacaoMecanico.getAvaliacao();
        int dias = avaliacaoMecanico.getDias();
        List<ServicoOrcamento> servicos = avaliacaoMecanico.getServicos();

        entity = this._serviceOrcamento.get_service().configurarAvaliacao(entity, avaliacao, mecanico, dias);
        entity = this._serviceOrcamento.get_service().configurarServicos(entity, servicos);
        entity = this._serviceOrcamento.get_service().configurarSituacaoOrcamento(entity);

        _serviceOrcamento.get_service().save(entity);        

        return ResponseEntity.ok(entity);
    }

    @GetMapping("semconcerto")
    @ApiOperation(value = "O Avaliador indica que o veículo não tem conserto")
    public ResponseEntity<Orcamento> semconcerto(
            @ApiParam(example = "xxxxxxxxxx", value = "cpf do mecânico cadastrado") 
            @RequestParam(name = "mecanicoCpf") String mecanicoCpf,
            @ApiParam(example = "Tafarel Rivelino Ronaldo dinho 123123", 
            value = "Código de Identificação: default:  ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo")
            @RequestParam(name = "identificacao") String identificacao) {
        Mecanico mecanico = _serviceMecanico.findValidExistsByCpf(mecanicoCpf);
        
        Orcamento entity = this._serviceOrcamento.get_service().veiculoSemConcerto(identificacao, mecanico);

        _serviceOrcamento.get_service().update(entity);

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