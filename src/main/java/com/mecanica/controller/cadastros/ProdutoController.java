package com.mecanica.controller.cadastros;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.application.validation.categoriaProduto.CategoriaProdutoValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.categoria.CategoriaProduto;
import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.domain.services.produto.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/produto")
public class ProdutoController extends BaseController {

    private final ProdutoService _serviceProduto;
    private final CategoriaProdutoValidations _serviceCategoriaProduto;

    @Autowired
    public ProdutoController(
        ProdutoService service,
        CategoriaProdutoValidations serviceCategoriaProduto) {
        _serviceProduto = service;
        _serviceCategoriaProduto = serviceCategoriaProduto;
    }

    @ExceptionHandler(RegraBaseException.class)
    public ResponseEntity<String> error(RegraBaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @GetMapping("list/filter")
    public ResponseEntity<Page<Produto>> listFilter(
        @RequestParam(name = "page") int page, 
        @RequestBody Produto cliente) {

        Page<Produto> list = this._serviceProduto.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    public ResponseEntity<Page<Produto>> list(@RequestParam(name = "page") int page) {

        Page<Produto> list = this._serviceProduto.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Produto> find(@RequestParam(name = "id") String id) {

        Produto entity = this._serviceProduto.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Produto> save(@RequestBody @Valid Produto entity) {
        String categoriaId = entity.getCategoria().getId().toString();
        CategoriaProduto categoria = _serviceCategoriaProduto.findValidExistsById(categoriaId);
        
        entity = this._serviceProduto.save(entity, categoria);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Produto> update(@RequestBody @Valid Produto entity) {
        String categoriaId = entity.getCategoria().getId().toString();
        CategoriaProduto categoria = _serviceCategoriaProduto.findValidExistsById(categoriaId);

        entity = this._serviceProduto.update(entity, categoria);

        return ResponseEntity.ok(entity);
    }
}