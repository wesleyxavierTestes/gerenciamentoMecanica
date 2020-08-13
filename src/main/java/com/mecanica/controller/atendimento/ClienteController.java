package com.mecanica.controller.atendimento;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.services.cliente.ClienteService;

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
@RequestMapping("/api/cliente")
public class ClienteController extends BaseController {

    private final ClienteService _serviceCliente;

    @Autowired
    public ClienteController(ClienteService clienteComum) {
        _serviceCliente = clienteComum;
    }

    @GetMapping("list/filter")
    public ResponseEntity<Page<Cliente>> listFilter(
        @RequestParam(name = "page") int page, 
        @RequestBody Cliente cliente) {

        Page<Cliente> list = this._serviceCliente.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    public ResponseEntity<Page<Cliente>> list(@RequestParam(name = "page") int page) {

        Page<Cliente> list = this._serviceCliente.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/filter/nome")
    public ResponseEntity<Page<Cliente>> listFilter(
        @RequestParam(name = "page") int page, @RequestParam(name = "nome") String nome) {

        Page<Cliente> list = this._serviceCliente.findAllByNomeContains(nome, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Cliente> find(@RequestParam(name = "id") String id) {

        Cliente entity = this._serviceCliente.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    @ApiOperation(
        value = "Salva um Cliente", 
        response = Cliente.class
    )
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente entity) {

        _serviceCliente.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Cliente> update(@RequestBody Cliente entity) {

        entity = this._serviceCliente.update(entity);

        return ResponseEntity.ok(entity);
    }
}