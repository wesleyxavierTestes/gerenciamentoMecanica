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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/ordemServico")
public class OrdemServicoController extends BaseController {

    private final OrdemServicoService _serviceOrdemServico;

    @Autowired
    public OrdemServicoController(OrdemServicoService ordemServicoComum) {
        _serviceOrdemServico = ordemServicoComum;
    }

    @GetMapping("list/filter")
    @ApiOperation(value = "Lista models mediante paginação e Filtra mediante parametros do _model_. Default: 10 itens")
    public ResponseEntity<Page<OrdemServico>> listFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @RequestBody OrdemServico cliente) {

        Page<OrdemServico> list = this._serviceOrdemServico.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    @ApiOperation(value = "Lista models mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<OrdemServico>> list(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page) {

        Page<OrdemServico> list = this._serviceOrdemServico.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    @ApiOperation(value = "Busca um único _model_ referente ao específico id")
    public ResponseEntity<OrdemServico> find(@ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do _model_ cadastrado") 
    @RequestParam(name = "id") String id) {

        OrdemServico entity = this._serviceOrdemServico.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    @ApiOperation(value = "Salva _model_ se itens necessários estiverem válidos")
    public ResponseEntity<OrdemServico> save(@RequestBody @Valid OrdemServico entity) {

        _serviceOrdemServico.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    @ApiOperation(value = "Altera _model_  já cadastrado se itens necessários estiverem válidos")
    public ResponseEntity<OrdemServico> update(@RequestBody @Valid OrdemServico entity) {

        entity = this._serviceOrdemServico.update(entity);

        return ResponseEntity.ok(entity);
    }
}