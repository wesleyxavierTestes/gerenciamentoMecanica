package com.mecanica.controller.avaliacao;

import javax.validation.Valid;

import com.mecanica.application.dto.avaliacao.AvaliacaoMecanicoDto;
import com.mecanica.application.validation.cliente.ClienteValidations;
import com.mecanica.application.validation.funcionario.FuncionarioValidations;
import com.mecanica.application.validation.mecanico.MecanicoValidations;
import com.mecanica.application.validation.veiculo.VeiculoValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.cliente.ClienteHistoricoRetorno;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.services.cliente.ClienteHistoricoRetornoService;
import com.mecanica.domain.services.ordemServico.orcamento.OrcamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/avaliacao")
public class AvaliacaoController extends BaseController {

    private final OrcamentoService _serviceOrcamento;
    private final FuncionarioValidations _serviceFuncionario;
    private final MecanicoValidations _serviceMecanico;
    private final ClienteValidations _serviceCliente;
    private final VeiculoValidations _serviceVeiculo;
    private final ClienteHistoricoRetornoService _clienteHistoricoRetornoService;

    @Autowired
    public AvaliacaoController(OrcamentoService orcamentoComum, ClienteValidations serviceCliente,
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
    public ResponseEntity<Page<Orcamento>> list(@RequestParam(name = "page") int page) {

        Page<Orcamento> list = this._serviceOrcamento.findAllSituacao(EnumSituacaoOrcamento.Aguardando, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("pedidoAvaliacao")
    public ResponseEntity<Orcamento> pedidoAvaliacao(
            @RequestParam(name = "funcionarioCpf") String funcionarioCpf,
            @RequestParam(name = "clienteId") String clienteId,
            @RequestParam(name = "veiculoRenavam") String veiculoRenavam,
            @RequestParam(name = "causas") String causas) {
        Funcionario atendente = _serviceFuncionario.findValidExistsByCpf(funcionarioCpf);
        Cliente cliente = _serviceCliente.findValidExistsById(clienteId);
        Veiculo veiculo = _serviceVeiculo.findValidExistsByRenavam(veiculoRenavam);
        Orcamento entity = this._serviceOrcamento.criarPedidoAvaliacao(atendente, cliente, veiculo, causas);

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
    public ResponseEntity<Orcamento> incluirAvaliacao(
            @RequestParam(name = "mecanicoCpf") String mecanicoCpf,
            @RequestParam(name = "identificacao") String identificacao,
            @RequestBody @Valid AvaliacaoMecanicoDto avaliacaoMecanico) {

        Mecanico mecanico = _serviceMecanico.findValidExistsByCpf(mecanicoCpf);
        Orcamento entity = this._serviceOrcamento.findByIdentificacao(identificacao);

        entity = this._serviceOrcamento.configurarAvaliacao(entity, avaliacaoMecanico.getAvaliacao(), mecanico);
        entity = this._serviceOrcamento.configurarServicos(entity, avaliacaoMecanico.getServicos());
        entity = this._serviceOrcamento.configurarSituacaoOrcamento(entity);

        _serviceOrcamento.save(entity);

        return ResponseEntity.ok(entity);
    }

    @GetMapping("semconcerto")
    public ResponseEntity<Orcamento> semconcerto(
        @RequestParam(name = "mecanicoCpf") String mecanicoCpf,
        @RequestParam(name = "identificacao") String identificacao
        ) {
        Mecanico mecanico = _serviceMecanico.findValidExistsByCpf(mecanicoCpf);
        Orcamento entity = this._serviceOrcamento.veiculoSemConcerto(identificacao, mecanico);

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
    public ResponseEntity<ClienteHistoricoRetorno> informarCliente(
        @RequestParam(name = "funcionarioCpf") String funcionarioCpf,
        @RequestBody @Valid ClienteHistoricoRetorno clienteHistoricoRetorno) {

        Funcionario atendente = _serviceFuncionario.findValidExistsByCpf(funcionarioCpf);
        Orcamento orcamento = this._serviceOrcamento.findByIdentificacao(clienteHistoricoRetorno.getIdentificacao());

        ClienteHistoricoRetorno entity = this._clienteHistoricoRetornoService.informarCliente(atendente, orcamento,
                clienteHistoricoRetorno);

        _clienteHistoricoRetornoService.save(entity);

        return ResponseEntity.ok(entity);
    }
}