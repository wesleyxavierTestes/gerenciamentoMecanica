package com.mecanica.controller.avaliacao;

import java.util.UUID;

import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.services.ordemServico.orcamento.OrcamentoService;

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
@RequestMapping("/api/orcamento")
public class OrcamentoController extends BaseController {

    private final OrcamentoService _serviceOrcamento;

    @Autowired
    public OrcamentoController(OrcamentoService orcamentoComum) {
        _serviceOrcamento = orcamentoComum;
    }

    @GetMapping("list")
    public ResponseEntity<Page<Orcamento>> list(@RequestParam(name = "page") int page) {

        Page<Orcamento> list = this._serviceOrcamento.list(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Orcamento> find(@RequestParam(name = "id") String id) {

        Orcamento entity = this._serviceOrcamento.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Object> saveServico(@RequestBody Orcamento entity) {
        if (!validations.by(entity).isValid())
            return ResponseEntity.ok(validations.getErros());

        _serviceOrcamento.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody Orcamento entity) {
        if (!validations.by(entity).isValid())
            return ResponseEntity.ok(validations.getErros());

        entity = this._serviceOrcamento.update(entity);

        return ResponseEntity.ok(entity);
    }
}