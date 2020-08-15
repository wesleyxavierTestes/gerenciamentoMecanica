package com.mecanica.controller.cadastros;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.applicationServices.categoriaServico.CategoriaServicoValidations;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

    @GetMapping("list/filter/nome")
    @ApiOperation(value = "Lista models mediante paginação e filtra por pelo Nome do item. Default: 10 itens")
    public ResponseEntity<Page<Servico>> listFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") @RequestParam(name = "page") int page,
            @ApiParam(example = "Tafarel Rivelino Ronaldo dinho", value = "Nome do serviço cadastrado") @RequestParam(name = "nome") String nome) {

        Page<Servico> list = this._serviceServico.findAllByNomeContains(nome, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    @ApiOperation(value = "Lista models mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<Servico>> list(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") @RequestParam(name = "page") int page) {

        Page<Servico> list = this._serviceServico.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    @ApiOperation(value = "Busca um único _model_ referente ao específico id")
    public ResponseEntity<Servico> find(@ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do _model_ cadastrado") @RequestParam(name = "id") String id) {

        Servico entity = this._serviceServico.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    @ApiOperation(value = "Salva _model_ se itens necessários estiverem válidos")
    public ResponseEntity<Servico> save(@RequestBody @Valid Servico entity) {
        String categoriaId = entity.getCategoria().getId().toString();
        CategoriaServico categoria = _serviceCategoriaServico.findValidExistsById(categoriaId);

        _serviceServico.save(entity, categoria);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    @ApiOperation(value = "Altera _model_  já cadastrado se itens necessários estiverem válidos")
    public ResponseEntity<Servico> update(@RequestBody @Valid Servico entity) {
        String categoriaId = entity.getCategoria().getId().toString();
        CategoriaServico categoria = _serviceCategoriaServico.findValidExistsById(categoriaId);

        entity = this._serviceServico.update(entity, categoria);

        return ResponseEntity.ok(entity);
    }
}