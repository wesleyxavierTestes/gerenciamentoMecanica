package com.mecanica.controller.servico;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.services.ordemServico.ordemServico.OrdemServicoService;

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
@RequestMapping("/api/ordemServico")
public class OrdemServicoController extends BaseController {

    private final OrdemServicoService _serviceOrdemServico;

    @Autowired
    public OrdemServicoController(OrdemServicoService ordemServicoComum) {
        _serviceOrdemServico = ordemServicoComum;
    }

    @GetMapping("list/filter")
    public ResponseEntity<Page<OrdemServico>> listFilter(
        @RequestParam(name = "page") int page, 
        @RequestBody OrdemServico cliente) {

        Page<OrdemServico> list = this._serviceOrdemServico.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    public ResponseEntity<Page<OrdemServico>> list(@RequestParam(name = "page") int page) {

        Page<OrdemServico> list = this._serviceOrdemServico.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<OrdemServico> find(@RequestParam(name = "id") String id) {

        OrdemServico entity = this._serviceOrdemServico.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<OrdemServico> save(@RequestBody @Valid OrdemServico entity) {

        _serviceOrdemServico.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<OrdemServico> update(@RequestBody @Valid OrdemServico entity) {

        entity = this._serviceOrdemServico.update(entity);

        return ResponseEntity.ok(entity);
    }
}