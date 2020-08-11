package com.mecanica.controller.atendimento;

import java.util.UUID;

import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.veiculo.VeiculoService;

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
@RequestMapping("/api/veiculo")
public class VeiculoController extends BaseController {

    private final VeiculoService _serviceVeiculo;

    @Autowired
    public VeiculoController(VeiculoService veiculoComum) {
        _serviceVeiculo = veiculoComum;
    }

    @GetMapping("list")
    public ResponseEntity<Page<Veiculo>> list(@RequestParam(name = "page") int page) {

        Page<Veiculo> list = this._serviceVeiculo.list(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Veiculo> find(@RequestParam(name = "id") String id) {

        Veiculo entity = this._serviceVeiculo.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    public ResponseEntity<Object> saveServico(@RequestBody Veiculo entity) {
        if (!validations.by(entity).isValid())
            return ResponseEntity.ok(validations.getErros());

        _serviceVeiculo.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody Veiculo entity) {
        if (!validations.by(entity).isValid())
            return ResponseEntity.ok(validations.getErros());

        entity = this._serviceVeiculo.update(entity);

        return ResponseEntity.ok(entity);
    }
}