package com.mecanica.controller.cadastros;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.services.mecanico.MecanicoService;

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
@RequestMapping("/api/mecanico")
public class MecanicoController extends BaseController {

    private final MecanicoService _serviceMecanico;

    @Autowired
    public MecanicoController(MecanicoService mecanicoComum) {
        _serviceMecanico = mecanicoComum;
    }

    @GetMapping("list")
    public ResponseEntity<Page<Mecanico>> list(@RequestParam(name = "page") int page) {

        Page<Mecanico> list = this._serviceMecanico.list(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Mecanico> find(@RequestParam(name = "id") String id) {

        Mecanico entity = this._serviceMecanico.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Object> saveServico(@RequestBody @Valid Mecanico entity) {

        _serviceMecanico.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody @Valid Mecanico entity) {

        entity = this._serviceMecanico.update(entity);

        return ResponseEntity.ok(entity);
    }
}