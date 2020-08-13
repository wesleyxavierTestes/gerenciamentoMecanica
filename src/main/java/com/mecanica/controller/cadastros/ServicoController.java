package com.mecanica.controller.cadastros;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.validation.categoriaServico.CategoriaServicoValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.categoria.CategoriaServico;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.services.servico.ServicoService;

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
@RequestMapping("/api/servico")
public class ServicoController extends BaseController {

    private final ServicoService _serviceServico;
    private final CategoriaServicoValidations _serviceCategoriaServico;

    @Autowired
    public ServicoController(ServicoService mecanicoComum, CategoriaServicoValidations serviceCategoriaServico) {
        _serviceServico = mecanicoComum;
        _serviceCategoriaServico = serviceCategoriaServico;
    }

    @GetMapping("list")
    public ResponseEntity<Page<Servico>> list(@RequestParam(name = "page") int page) {

        Page<Servico> list = this._serviceServico.list(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Servico> find(@RequestParam(name = "id") String id) {

        Servico entity = this._serviceServico.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Object> saveServico(@RequestBody @Valid Servico entity) {
        String categoriaId = entity.getCategoria().getId().toString();
        CategoriaServico categoria = _serviceCategoriaServico.findValidExistsById(categoriaId);

        _serviceServico.save(entity, categoria);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody @Valid Servico entity) {
        String categoriaId = entity.getCategoria().getId().toString();
        CategoriaServico categoria = _serviceCategoriaServico.findValidExistsById(categoriaId);

        entity = this._serviceServico.update(entity, categoria);

        return ResponseEntity.ok(entity);
    }
}