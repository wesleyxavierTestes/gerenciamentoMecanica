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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController extends BaseController {

    private final FuncionarioService _serviceFuncionario;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioComum) {
        _serviceFuncionario = funcionarioComum;
    }

    @GetMapping("list/filter")
    @ApiOperation(value = "Lista models mediante paginação e Filtra mediante parametros do _model_. Default: 10 itens")
    public ResponseEntity<Page<Funcionario>> listFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @RequestBody Funcionario cliente) {

        Page<Funcionario> list = this._serviceFuncionario.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    @ApiOperation(value = "Lista models mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<Funcionario>> list(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page) {

        Page<Funcionario> list = this._serviceFuncionario.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    @ApiOperation(value = "Busca um único _model_ referente ao específico id")
    public ResponseEntity<Funcionario> find(
        @ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do _model_ cadastrado") 
        @RequestParam(name = "id") String id) {

        Funcionario entity = this._serviceFuncionario.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    @ApiOperation(value = "Salva _model_ se itens necessários estiverem válidos")
    public ResponseEntity<Funcionario> save(@RequestBody @Valid Funcionario entity) {

        _serviceFuncionario.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    @ApiOperation(value = "Altera _model_  já cadastrado se itens necessários estiverem válidos")
    public ResponseEntity<Funcionario> update(@RequestBody @Valid Funcionario entity) {

        entity = this._serviceFuncionario.update(entity);

        return ResponseEntity.ok(entity);
    }
}