package com.mecanica.controller.cadastros;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.services.funcionario.FuncionarioService;

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
@RequestMapping("/api/funcionario")
public class FuncionarioController extends BaseController {

    private final FuncionarioService _serviceFuncionario;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioComum) {
        _serviceFuncionario = funcionarioComum;
    }

    @GetMapping("list/filter")
    public ResponseEntity<Page<Funcionario>> listFilter(
        @RequestParam(name = "page") int page, 
        @RequestBody Funcionario cliente) {

        Page<Funcionario> list = this._serviceFuncionario.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    public ResponseEntity<Page<Funcionario>> list(@RequestParam(name = "page") int page) {

        Page<Funcionario> list = this._serviceFuncionario.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Funcionario> find(@RequestParam(name = "id") String id) {

        Funcionario entity = this._serviceFuncionario.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Funcionario> save(@RequestBody @Valid Funcionario entity) {

        _serviceFuncionario.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Funcionario> update(@RequestBody @Valid Funcionario entity) {

        entity = this._serviceFuncionario.update(entity);

        return ResponseEntity.ok(entity);
    }
}