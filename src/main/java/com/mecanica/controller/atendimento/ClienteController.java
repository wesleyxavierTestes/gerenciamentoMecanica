package com.mecanica.controller.atendimento;

import java.util.UUID;

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

    @GetMapping("list")
    public ResponseEntity<Page<Cliente>> list(@RequestParam(name = "page") int page) {

        Page<Cliente> list = this._serviceCliente.list(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/filter")
    public ResponseEntity<Page<Cliente>> listFilter(@RequestParam(name = "page") int page, @RequestParam(name = "nome") String nome) {

        Page<Cliente> list = this._serviceCliente.findAllByNome(nome, page);

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
    public ResponseEntity<Object> save(@RequestBody Cliente entity) {
        if (!validations.by(entity).isValid())
            return ResponseEntity.ok(validations.getErros());

        _serviceCliente.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody Cliente entity) {
        if (!validations.by(entity).isValid())
            return ResponseEntity.ok(validations.getErros());

        entity = this._serviceCliente.update(entity);

        return ResponseEntity.ok(entity);
    }
}