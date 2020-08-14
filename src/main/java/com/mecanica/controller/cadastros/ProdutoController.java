package com.mecanica.controller.cadastros;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.validation.categoriaProduto.CategoriaProdutoValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.categoria.CategoriaProduto;
import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.domain.services.produto.ProdutoService;

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
@RequestMapping("api/produto")
public class ProdutoController extends BaseController {

    private final ProdutoService _serviceProduto;
    private final CategoriaProdutoValidations _serviceCategoriaProduto;

    @Autowired
    public ProdutoController(ProdutoService service, CategoriaProdutoValidations serviceCategoriaProduto) {
        _serviceProduto = service;
        _serviceCategoriaProduto = serviceCategoriaProduto;
    }

    @GetMapping("list/filter")
    @ApiOperation(value = "Lista models mediante paginação e Filtra mediante parametros do _model_. Default: 10 itens")
    public ResponseEntity<Page<Produto>> listFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") @RequestParam(name = "page") int page,
            @RequestBody Produto cliente) {

        Page<Produto> list = this._serviceProduto.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    @ApiOperation(value = "Lista models mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<Produto>> list(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1")
            @RequestParam(name = "page") int page) {

        Page<Produto> list = this._serviceProduto.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    @ApiOperation(value = "Busca um único _model_ referente ao específico id")
    public ResponseEntity<Produto> find(@ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do _model_ cadastrado") @RequestParam(name = "id") String id) {

        Produto entity = this._serviceProduto.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    @ApiOperation(value = "Salva _model_ se itens necessários estiverem válidos")
    public ResponseEntity<Produto> save(@RequestBody @Valid Produto entity) {
        String categoriaId = entity.getCategoria().getId().toString();
        CategoriaProduto categoria = _serviceCategoriaProduto.findValidExistsById(categoriaId);

        entity = this._serviceProduto.save(entity, categoria);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    @ApiOperation(value = "Altera _model_  já cadastrado se itens necessários estiverem válidos")
    public ResponseEntity<Produto> update(@RequestBody @Valid Produto entity) {
        String categoriaId = entity.getCategoria().getId().toString();
        CategoriaProduto categoria = _serviceCategoriaProduto.findValidExistsById(categoriaId);

        entity = this._serviceProduto.update(entity, categoria);

        return ResponseEntity.ok(entity);
    }
}