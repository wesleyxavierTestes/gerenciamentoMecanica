package com.mecanica.controller.cadastros;

import javax.validation.Valid;

import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.categoria.CategoriaProduto;
import com.mecanica.domain.entities.categoria.CategoriaServico;
import com.mecanica.domain.services.categoria.CategoriaProdutoService;
import com.mecanica.domain.services.categoria.CategoriaServicoService;

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
@RequestMapping("api/categoria")
public class CategoriaController extends BaseController {

    private final CategoriaServicoService _categoriaServico;
    private final CategoriaProdutoService _categoriaProduto;

    @Autowired
    public CategoriaController(CategoriaServicoService categoriaServico, CategoriaProdutoService categoriaProduto) {
        _categoriaServico = categoriaServico;
        _categoriaProduto = categoriaProduto;
    }

    @GetMapping("list/servico")
    @ApiOperation(value = "Lista models do tipo serviço mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<CategoriaServico>> listServico(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page) {

        Page<CategoriaServico> list = this._categoriaServico.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/servico/filter")
    @ApiOperation(value = "Lista models do tipo serviço mediante paginação e filtra por nome. Default: 10 itens")
    public ResponseEntity<Page<CategoriaServico>> listServicoFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @ApiParam(example = "elétrica", value = "nome do _model_ cadastrado") 
            @RequestParam(name = "nome") String nome) {

        Page<CategoriaServico> list = this._categoriaServico.findAllByNomeContains(nome, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/produto")
    @ApiOperation(value = "Lista models do tipo produto mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<CategoriaProduto>> listProduto(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page) {

        Page<CategoriaProduto> list = this._categoriaProduto.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/produto/filter")
    @ApiOperation(value = "Lista models do tipo produto mediante paginação e filtra por nome. Default: 10 itens")
    public ResponseEntity<Page<CategoriaProduto>> listProdutoFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @ApiParam(example = "elétrica", value = "nome do _model_ cadastrado") 
            @RequestParam(name = "nome") String nome) {

        Page<CategoriaProduto> list = this._categoriaProduto.findAllByNomeContainsIgnoreCase(nome, page);

        return ResponseEntity.ok(list);
    }

    @PostMapping("save/servico")
    @ApiOperation(value = "Registra um _model_ de serviço mediante validações")
    public ResponseEntity<CategoriaServico> saveServico(@RequestBody @Valid CategoriaServico entity) {

        _categoriaServico.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save/produto")
    @ApiOperation(value = "Registra um _model_ de produto mediante validações")
    public ResponseEntity<CategoriaProduto> saveProduto(@RequestBody() @Valid CategoriaProduto entity) {

        _categoriaProduto.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update/produto")
    @ApiOperation(value = "Altera um Registra de _model_ de produto mediante validações")
    public ResponseEntity<CategoriaProduto> update(@RequestBody @Valid CategoriaProduto entity) {

        entity = this._categoriaProduto.update(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update/servico")
    @ApiOperation(value = "Altera um Registra de _model_ de serviço mediante validações")
    public ResponseEntity<CategoriaServico> update(@RequestBody @Valid CategoriaServico entity) {

        entity = this._categoriaServico.update(entity);

        return ResponseEntity.ok(entity);
    }
}