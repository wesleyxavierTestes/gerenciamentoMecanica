package com.mecanica.controller.atendimento;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.validation.cliente.ClienteValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.veiculo.VeiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController extends BaseController {

    private final VeiculoService _serviceVeiculo;
    private final ClienteValidations _serviceCliente;

    @Autowired
    public VeiculoController(VeiculoService veiculoComum, ClienteValidations serviceCliente) {
        _serviceVeiculo = veiculoComum;
        _serviceCliente = serviceCliente;
    }

    @GetMapping("list")
    public ResponseEntity<Page<Veiculo>> list(@RequestParam(name = "page") int page) {

        Page<Veiculo> list = this._serviceVeiculo.list(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Veiculo> find(@RequestParam(name = "id") String id) {

        Veiculo entity = this._serviceVeiculo.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Object> saveServico(@RequestBody @Valid Veiculo entity) {

        Cliente cliente = _serviceCliente.findValidExistsById(entity.getCliente().getId().toString());

        entity = _serviceVeiculo.save(entity, cliente);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody @Valid Veiculo entity) {

        Cliente cliente = _serviceCliente.findValidExistsById(entity.getCliente().getId().toString());

        entity = this._serviceVeiculo.update(entity, cliente);

        return ResponseEntity.ok(entity);
    }
}