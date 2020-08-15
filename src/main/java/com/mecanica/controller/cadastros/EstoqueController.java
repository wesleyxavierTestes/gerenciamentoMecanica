package com.mecanica.controller.cadastros;

import java.util.UUID;

import javax.validation.Valid;

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

    @GetMapping("list")
    @ApiOperation(value = "Lista models mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<AbstractEstoque>> list(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page) {

        Page<AbstractEstoque> list = this._service.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/entrada")
    @ApiOperation(value = "Lista models de entrada mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<EstoqueEntrada>> listEntrada(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page) {

        Page<EstoqueEntrada> list = this._serviceEntrada.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/saida")
    @ApiOperation(value = "Lista models de saída mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<EstoqueSaida>> listSaida(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page) {

        Page<EstoqueSaida> list = this._serviceSaida.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    @ApiOperation(value = "Busca um único _model_ referente ao específico id")
    public ResponseEntity<AbstractEstoque> find(
        @ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do _model_ cadastrado") 
        @RequestParam(name = "id") String id) {

        AbstractEstoque entity = this._service.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save/entrada")
    @ApiOperation(value = "Registra um _model_ de entrada mediante validações")
    public ResponseEntity<EstoqueEntrada> saveEntrada(@RequestBody @Valid EstoqueEntrada entity) {
        ;

        entity = this._serviceEntrada.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update/entrada")
    @ApiOperation(value = "Altera um Registro um _model_ de entrada mediante validações")
    public ResponseEntity<EstoqueEntrada> updateEntrada(@RequestBody @Valid EstoqueEntrada entity) {

        entity = this._serviceEntrada.update(entity);

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save/saida")
    @ApiOperation(value = "Registra um _model_ de saída mediante validações")
    public ResponseEntity<EstoqueSaida> saveSaida(@RequestBody @Valid EstoqueSaida entity) {

        entity = this._serviceSaida.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update/saida")
    @ApiOperation(value = "Altera um Registro um _model_ de saída mediante validações")
    public ResponseEntity<EstoqueSaida> updateSaida(@RequestBody @Valid EstoqueSaida entity) {

        entity = this._serviceSaida.update(entity);

        return ResponseEntity.ok(entity);
    }
}