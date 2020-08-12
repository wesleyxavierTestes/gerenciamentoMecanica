package com.mecanica.controller.avaliacao;

import java.util.UUID;

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
import com.mecanica.domain.services.cliente.ClienteService;
import com.mecanica.domain.services.funcionario.FuncionarioService;
import com.mecanica.domain.services.mecanico.MecanicoService;
import com.mecanica.domain.services.ordemServico.orcamento.OrcamentoService;
import com.mecanica.domain.services.veiculo.VeiculoService;

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
    private final FuncionarioService _serviceFuncionario;
    private final MecanicoService _serviceMecanico;
    private final ClienteService _serviceCliente;
    private final VeiculoService _serviceVeiculo;

    @Autowired
    public AvaliacaoController(OrcamentoService orcamentoComum, ClienteService serviceCliente,
            VeiculoService serviceVeiculo, FuncionarioService serviceFuncionario, MecanicoService serviceMecanico) {
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

    @GetMapping("criarPedidoAvaliacao")
    public ResponseEntity<Object> criarPedidoAvaliacao(@RequestParam(name = "funcionarioId") String funcionarioId,
            @RequestParam(name = "clienteId") String clienteId, @RequestParam(name = "veiculoId") String veiculoId,
            @RequestParam(name = "causas") String causas) {

        Funcionario atendente = new FuncionarioValidations(this._serviceFuncionario)
                .findValidExistsById(funcionarioId);

        Cliente cliente = new ClienteValidations(this._serviceCliente).findValidExistsById(clienteId);
        Veiculo veiculo = new VeiculoValidations(this._serviceVeiculo).findValidExistsById(veiculoId);

        Orcamento entity = this._serviceOrcamento.criarPedidoAvaliacao(atendente, cliente, veiculo, causas);

        _serviceOrcamento.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Object> save(@RequestParam(name = "mecanicoId") String mecanicoId,
            @RequestParam(name = "orcamentoId") String orcamentoId, @RequestBody @Valid Avaliacao avaliacao) {
        Mecanico mecanico = new MecanicoValidations(this._serviceMecanico).findValidExistsById(mecanicoId);

        Orcamento entity = this._serviceOrcamento.configurarAvaliacao(orcamentoId, avaliacao, mecanico);

        _serviceOrcamento.save(entity);

        return ResponseEntity.ok(entity);
    }
}