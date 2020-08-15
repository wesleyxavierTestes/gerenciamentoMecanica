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
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController extends BaseController {

    private final ClienteService _serviceCliente;

    @Autowired
    public ClienteController(ClienteService clienteComum) {
        _serviceCliente = clienteComum;
    }

    @GetMapping("list/filter")
    @ApiOperation(value = "Lista models mediante paginação e Filtra mediante parametros do _model_. Default: 10 itens")
    public ResponseEntity<Page<Cliente>> listFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") @RequestParam(name = "page") int page,
            @RequestBody Cliente cliente) {

        Page<Cliente> list = this._serviceCliente.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    @ApiOperation(value = "Lista models mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<Cliente>> list(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") @RequestParam(name = "page") int page) {

        Page<Cliente> list = this._serviceCliente.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/filter/nome")
    @ApiOperation(value = "Lista models mediante paginação e filtra por pelo Nome do item. Default: 10 itens")
    public ResponseEntity<Page<Cliente>> listFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @ApiParam(example = "Tafarel Rivelino Ronaldo dinho", value = "Nome do cliente") @RequestParam(name = "nome") String nome) {

        Page<Cliente> list = this._serviceCliente.findAllByNomeContainsIgnoreCase(nome, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    @ApiOperation(value = "Busca um único _model_ referente ao específico id")
    public ResponseEntity<Cliente> find(@ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do _model_ cadastrado") @RequestParam(name = "id") String id) {

        Cliente entity = this._serviceCliente.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    @ApiOperation(value = "Salva _model_ se itens necessários estiverem válidos")
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente entity) {

        _serviceCliente.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    @ApiOperation(value = "Altera _model_  já cadastrado se itens necessários estiverem válidos")
    public ResponseEntity<Cliente> update(@RequestBody Cliente entity) {

        entity = this._serviceCliente.update(entity);

        return ResponseEntity.ok(entity);
    }
}