package com.mecanica.controller.cadastros;

import java.util.UUID;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.controller.BaseController;
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

    private final ProdutoService _service;

    @Autowired
    public ProdutoController(ProdutoService service) {
        _service = service;
    }

    @ExceptionHandler(RegraBaseException.class)
    public ResponseEntity<String> error(RegraBaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @GetMapping("list")
    public ResponseEntity<Page<Produto>> list(@RequestParam(name = "page") int page) {

        Page<Produto> list = this._service.list(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Produto> find(@RequestParam(name = "id") String id) {

        Produto entity = this._service.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Object> save(@RequestBody Produto entity) {
        if (!validations.by(entity).isValid())
            return ResponseEntity.ok(validations.getErros());

        entity = this._service.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody Produto entity) {
        if (!validations.by(entity).isValid())
            return ResponseEntity.ok(validations.getErros());

        entity = this._service.update(entity);

        return ResponseEntity.ok(entity);
    }
}