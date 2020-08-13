package com.mecanica.controller.atendimento;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.validation.cliente.ClienteValidations;
import com.mecanica.application.validation.veiculo.VeiculoValidations;
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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController extends BaseController {

    private final VeiculoService _service;
    private final VeiculoValidations _serviceVeiculo;
    private final ClienteValidations _serviceCliente;

    @Autowired
    public VeiculoController(VeiculoService veiculo, VeiculoValidations serviceVeiculo, ClienteValidations serviceCliente) {
        _service = veiculo;
        _serviceCliente = serviceCliente;
        _serviceVeiculo = serviceVeiculo;
    }

    @GetMapping("list/filter")
    public ResponseEntity<Page<Veiculo>> listFilter(
        @RequestParam(name = "page") int page, 
        @RequestBody Veiculo cliente) {

        Page<Veiculo> list = this._service.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    public ResponseEntity<Page<Veiculo>> list(@RequestParam(name = "page") int page) {

        Page<Veiculo> list = this._service.findAll(page);

        return ResponseEntity.ok(list);
    }

    /**
     * Busca todos os veiculos do cliente
     * @param page
     * @param clienteId
     * @return
     */
    @GetMapping("list/cliente")
    @ApiOperation(
        value = "Busca todos os veiculos do cliente"
    )
    public ResponseEntity<Page<Veiculo>> listCliente(
        @RequestParam(name = "page") int page,
        @RequestParam(name = "clienteId") String clienteId
        ) {

        Page<Veiculo> list = this._service.findAllByClienteId((clienteId), page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Veiculo> find(@RequestParam(name = "id") String id) {

        Veiculo entity = this._service.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Veiculo> save(@RequestBody @Valid Veiculo entity) {

        Cliente cliente = _serviceCliente.findValidExistsById(entity.getCliente().getId().toString());

        entity = _service.save(entity, cliente);

        return ResponseEntity.ok(entity);
    }

    /**
     * Valida Se cliente existe
     * Valida se veiculo pertence ao Cliente informado
     * @param entity
     * @return
     */
    @PutMapping("update")
    public ResponseEntity<Veiculo> update(@RequestBody @Valid Veiculo entity) {

        Cliente cliente = _serviceCliente.findValidExistsById(entity.getCliente().getId().toString());
        Veiculo entityUpdate = _serviceVeiculo.validaClienteReferenteDoCarro(entity, cliente);

        entity = this._service.update(entity, entityUpdate);

        return ResponseEntity.ok(entity);
    }
}