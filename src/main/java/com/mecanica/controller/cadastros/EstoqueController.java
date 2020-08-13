package com.mecanica.controller.cadastros;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.estoque.AbstractEstoque;
import com.mecanica.domain.entities.estoque.EstoqueEntrada;
import com.mecanica.domain.entities.estoque.EstoqueSaida;
import com.mecanica.domain.services.estoque.EstoqueEntradaService;
import com.mecanica.domain.services.estoque.EstoqueSaidaService;
import com.mecanica.domain.services.estoque.EstoqueService;

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
@RequestMapping("api/estoque")
public class EstoqueController extends BaseController {

    private final EstoqueService _service;
    private final EstoqueSaidaService _serviceSaida;
    private final EstoqueEntradaService _serviceEntrada;

    @Autowired
    public EstoqueController(EstoqueService service, EstoqueSaidaService serviceSaida,
            EstoqueEntradaService serviceEntrada) {
        _service = service;
        _serviceSaida = serviceSaida;
        _serviceEntrada = serviceEntrada;
    }

    @ExceptionHandler(RegraBaseException.class)
    public ResponseEntity<String> error(RegraBaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @GetMapping("list")
    public ResponseEntity<Page<AbstractEstoque>> list(@RequestParam(name = "page") int page) {

        Page<AbstractEstoque> list = this._service.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/entrada")
    public ResponseEntity<Page<EstoqueEntrada>> listEntrada(@RequestParam(name = "page") int page) {

        Page<EstoqueEntrada> list = this._serviceEntrada.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/saida")
    public ResponseEntity<Page<EstoqueSaida>> listSaida(@RequestParam(name = "page") int page) {

        Page<EstoqueSaida> list = this._serviceSaida.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<AbstractEstoque> find(@RequestParam(name = "id") String id) {

        AbstractEstoque entity = this._service.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save/entrada")
    public ResponseEntity<EstoqueEntrada> saveEntrada(@RequestBody @Valid EstoqueEntrada entity) {;

        entity = this._serviceEntrada.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update/entrada")
    public ResponseEntity<EstoqueEntrada> updateEntrada(@RequestBody @Valid EstoqueEntrada entity) {

        entity = this._serviceEntrada.update(entity);

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save/saida")
    public ResponseEntity<EstoqueSaida> saveSaida(@RequestBody @Valid EstoqueSaida entity) {

        entity = this._serviceSaida.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update/saida")
    public ResponseEntity<EstoqueSaida> updateSaida(@RequestBody @Valid EstoqueSaida entity) {

        entity = this._serviceSaida.update(entity);

        return ResponseEntity.ok(entity);
    }
}