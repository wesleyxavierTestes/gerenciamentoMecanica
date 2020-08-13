package com.mecanica.controller.avaliacao;

import javax.validation.Valid;

import com.mecanica.application.validation.cliente.ClienteValidations;
import com.mecanica.application.validation.funcionario.FuncionarioValidations;
import com.mecanica.application.validation.mecanico.MecanicoValidations;
import com.mecanica.application.validation.veiculo.VeiculoValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.veiculo.Veiculo;
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

    @Autowired
    public AvaliacaoController(OrcamentoService orcamentoComum, ClienteValidations serviceCliente,
            VeiculoValidations serviceVeiculo, FuncionarioValidations serviceFuncionario,
            MecanicoValidations serviceMecanico) {
        _serviceOrcamento = orcamentoComum;
        _serviceCliente = serviceCliente;
        _serviceVeiculo = serviceVeiculo;
        _serviceFuncionario = serviceFuncionario;
        _serviceMecanico = serviceMecanico;
    }

    @GetMapping("list")
    public ResponseEntity<Page<Orcamento>> list(@RequestParam(name = "page") int page) {

        Page<Orcamento> list = this._serviceOrcamento.list(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("pedidoAvaliacao")
    public ResponseEntity<Object> pedidoAvaliacao(
            @RequestParam(name = "funcionarioCpf") String funcionarioCpf,
            @RequestParam(name = "clienteId") String clienteId, 
            @RequestParam(name = "veiculoId") String veiculoId,
            @RequestParam(name = "causas") String causas) {
        Funcionario atendente = _serviceFuncionario.findValidExistsByCpf(funcionarioCpf);
        Cliente cliente = _serviceCliente.findValidExistsById(clienteId);
        Veiculo veiculo = _serviceVeiculo.findValidExistsById(veiculoId);

        Orcamento entity = this._serviceOrcamento.criarPedidoAvaliacao(atendente, cliente, veiculo, causas);

        _serviceOrcamento.save(entity);

        return ResponseEntity.ok(entity);
    }

    /**
     * Valida Se existe mecânico
     * Configura Avaliação feito pelo mecanico
     * @param mecanicoCpf
     * @param orcamentoIdentificacao
     * @param avaliacao
     * @return
     */
    @PostMapping("save")
    public ResponseEntity<Object> save(
        @RequestParam(name = "mecanicoCpf") String mecanicoCpf,
        @RequestParam(name = "orcamentoIdentificacao") String orcamentoIdentificacao, 
        @RequestBody @Valid Avaliacao avaliacao) {
        
        Mecanico mecanico = _serviceMecanico.findValidExistsByCpf(mecanicoCpf);

        Orcamento entity = this._serviceOrcamento.configurarAvaliacao(orcamentoIdentificacao, avaliacao, mecanico);

        _serviceOrcamento.save(entity);

        return ResponseEntity.ok(entity);
    }
}