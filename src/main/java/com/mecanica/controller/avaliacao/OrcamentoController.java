package com.mecanica.controller.avaliacao;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.applicationServices.orcamento.OrcamentoValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.services.ordemServico.orcamento.OrcamentoService;
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
@RequestMapping("/api/orcamento")
public class OrcamentoController extends BaseController {

    private final OrcamentoService _serviceOrcamento;
    private final OrdemServicoService _serviceOrdemServico;
    private final OrcamentoValidations orcamentoValidations;

    @Autowired
    public OrcamentoController(OrcamentoService orcamentoComum, OrdemServicoService serviceOrdemServico,
            OrcamentoValidations orcamentoValidations) {
        _serviceOrcamento = orcamentoComum;
        _serviceOrdemServico = serviceOrdemServico;
        this.orcamentoValidations = orcamentoValidations;
    }

    @GetMapping("list/filter")
    @ApiOperation(value = "Lista models mediante paginação e Filtra mediante parametros do _model_. Default: 10 itens")
    public ResponseEntity<Page<Orcamento>> listFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @RequestBody Orcamento cliente) {

        Page<Orcamento> list = this._serviceOrcamento.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    @ApiOperation(value = "Lista models mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<Orcamento>> list(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page) {

        Page<Orcamento> list = this._serviceOrcamento.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/situacao")
    @ApiOperation(value = "Lista models mediante a situação e mediante a paginação. Default: 10 itens")
    public ResponseEntity<Page<Orcamento>> listAceites(
        @ApiParam(example = "Aceito", value = "Situação do Orçamento")
        @RequestParam(name = "situacao") EnumSituacaoOrcamento situacao,
        @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
        @RequestParam(name = "page") int page) {

        Page<Orcamento> list = this._serviceOrcamento.findAllBySituacaoEquals(situacao, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    @ApiOperation(value = "Busca um único _model_ referente ao específico id")
    public ResponseEntity<Orcamento> find(
            @ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do _model_ cadastrado") 
            @RequestParam(name = "id") String id) {

        Orcamento entity = this._serviceOrcamento.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @GetMapping("find/identificacao")
    @ApiOperation(value = "Busca um único _model_ referente ao específica identificacao")
    public ResponseEntity<Orcamento> findIdentificacao(
            @ApiParam(example = "2512201012100", value = "identificacao do _model_ cadastrado") 
            @RequestParam(name = "identificacao") String identificacao) {

        Orcamento entity = this._serviceOrcamento.findByIdentificacaoEquals(identificacao);

        return ResponseEntity.ok(entity);
    }

    @GetMapping("list/cliente")
    @ApiOperation(value = "Lista models mediante paginação e filtra pelo nome do cliente. Default: 10 itens")
    public ResponseEntity<Page<Orcamento>> listCliente(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @ApiParam(example = "Tafarel Rivelino Ronaldo dinho", value = "Nome do cliente", required = false) 
            @RequestParam(name = "clienteNome") String clienteNome,
            @ApiParam(example = "xxxxxxxxxx", value = "Cpf ou Cnpj do cliente", required = false) 
            @RequestParam(name = "clienteCpfCnpj") String clienteCpfCnpj) {

        Page<Orcamento> list = this._serviceOrcamento.findAllByClienteIdOrNomeOrCpfOrCnpj(clienteNome, clienteCpfCnpj,
                page);

        return ResponseEntity.ok(list);
    }

    /**
     * 
     * @param identificacao
     * @return
     */
    @GetMapping("aceite")
    @ApiOperation(value = "Registra que o Cliente aceitou um orçamento e cria uma Ordem de Serviço")
    public ResponseEntity<Orcamento> aceite(
            @ApiParam(example = "Tafarel Rivelino Ronaldo dinho 123123", 
            value = "Código de Identificação: default: " +
            "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
            @RequestParam(name = "identificacao") String identificacao) {

        Orcamento entity = this._serviceOrcamento.aceitarOrcamento(identificacao);

        _serviceOrcamento.update(entity);

        OrdemServico ordemServico =_serviceOrdemServico.registrarNovaOrdemServicoViaOrcamento(entity);

       this._serviceOrdemServico.save(ordemServico);

        return ResponseEntity.ok(entity);
    }

    @GetMapping("rejeitar")
    @ApiOperation(value = "Registra que o cliente negou o orçamento")
    public ResponseEntity<Orcamento> rejeitar(
            @ApiParam(example = "Tafarel Rivelino Ronaldo dinho 123123", 
            value = "Código de Identificação: default: " +
            "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
            @RequestParam(name = "identificacao") String identificacao) {

        Orcamento entity = this._serviceOrcamento.rejeitarOrcamento(identificacao);

        _serviceOrcamento.update(entity);

        return ResponseEntity.ok(entity);
    }

    @GetMapping("cancelar")
    @ApiOperation(value = "Registra cancelamento do orçamento")
    public ResponseEntity<Orcamento> cancelar(
            @ApiParam(example = "Tafarel Rivelino Ronaldo dinho 123123", 
            value = "Código de Identificação: default: " +
            "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
            @RequestParam(name = "identificacao") String identificacao) {

        Orcamento entity = this._serviceOrcamento.cancelarOrcamento(identificacao);

        _serviceOrcamento.update(entity);

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    @ApiOperation(value = "Salva _model_ se itens necessários estiverem válidos")
    public ResponseEntity<Orcamento> save(@RequestBody @Valid Orcamento entity) {
        orcamentoValidations.valida(entity);

        _serviceOrcamento.save(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    @ApiOperation(value = "Altera _model_  já cadastrado se itens necessários estiverem válidos")
    public ResponseEntity<Orcamento> update(@RequestBody @Valid Orcamento entity) {
        orcamentoValidations.valida(entity);

        entity = this._serviceOrcamento.update(entity);

        return ResponseEntity.ok(entity);
    }
}