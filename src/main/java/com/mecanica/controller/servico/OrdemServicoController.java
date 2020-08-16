package com.mecanica.controller.servico;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.applicationServices.ordemServico.OrdemServicoValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.financeiro.FinanceiroEntrada;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.domain.entities.servico.Servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    private final OrdemServicoValidations _serviceOrdemServico;

    @Autowired
    public OrdemServicoController(OrdemServicoValidations ordemServicoComum) {
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

    @GetMapping("list/cliente")
    @ApiOperation(value = "Lista models mediante paginação e filtra pelo nome do cliente. Default: 10 itens")
    public ResponseEntity<Page<OrdemServico>> listCliente(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @ApiParam(example = "Tafarel Rivelino Ronaldo dinho", value = "Nome do cliente", required = false) 
            @RequestParam(name = "clienteNome") String clienteNome,
            @ApiParam(example = "xxxxxxxxxx", value = "Cpf ou Cnpj do cliente", required = false) 
            @RequestParam(name = "clienteCpfCnpj") String clienteCpfCnpj) {

        Page<OrdemServico> list = this._serviceOrdemServico.findAllByClienteIdOrNomeOrCpfOrCnpj(clienteNome, clienteCpfCnpj,
                page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    @ApiOperation(value = "Busca um único _model_ referente ao específico id")
    public ResponseEntity<OrdemServico> find(
            @ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do _model_ cadastrado") 
            @RequestParam(name = "id") String id) {

        OrdemServico entity = this._serviceOrdemServico.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    /**
     * 
     * @param identificacao
     * @return
     */
    @GetMapping("incluir/data")
    @ApiOperation(value = "Registra data trabalhada")
    public ResponseEntity<OrdemServico> incluirData(
            @ApiParam(example = "16082020122006", value = "Código de Identificação: default: "
                    + "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
            @RequestParam(name = "identificacao") String identificacao,
            @RequestParam(name = "datatrabalho") String data) {

        OrdemServico entity = this._serviceOrdemServico.findByIdentificacaoEquals(identificacao);

        this._serviceOrdemServico.incluirDataTrabalhada(entity, data);

        this._serviceOrdemServico.update(entity);

        return ResponseEntity.ok(entity);
    }

  /**
   * 
   * @param identificacao
   * @param data
   * @return
   */
   @GetMapping("incluir/inicio")
   @ApiOperation(value = "Registra Inicio serviço")
   public ResponseEntity<OrdemServico> incluirInicio(
           @ApiParam(example = "16082020122006", value = "Código de Identificação: default: "
                   + "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
           @RequestParam(name = "identificacao") String identificacao,
           @RequestParam(name = "inicio") String data) {

       OrdemServico entity = this._serviceOrdemServico.findByIdentificacaoEquals(identificacao);

       this._serviceOrdemServico.incluirInicio(entity, data);

       this._serviceOrdemServico.update(entity);

       return ResponseEntity.ok(entity);
   }

    /**
   * 
   * @param identificacao
   * @param data
   * @return
   */
  @GetMapping("incluir/finalizacao")
  @ApiOperation(value = "Registra Inicio serviço")
  public ResponseEntity<OrdemServico> incluirFinalizacao(
          @ApiParam(example = "16082020122006", value = "Código de Identificação: default: "
                  + "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
          @RequestParam(name = "identificacao") String identificacao,
          @RequestParam(name = "finalizacao") String data) {

      OrdemServico entity = this._serviceOrdemServico.findByIdentificacaoEquals(identificacao);

      this._serviceOrdemServico.incluirFinalizacao(entity, data);

      this._serviceOrdemServico.update(entity);

      return ResponseEntity.ok(entity);
  }

    @GetMapping("incluir/servico")
    @ApiOperation(value = "Incluir no Registro um novo servico")
    public ResponseEntity<OrdemServico> incluirServico(
            @ApiParam(example = "16082020122006", value = "Código de Identificação: default: "
                    + "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
            @RequestParam(name = "identificacao") String identificacao,
            @RequestParam(name = "servicoId") String servicoId) {

        OrdemServico entity = this._serviceOrdemServico.findByIdentificacaoEquals(identificacao);

        Servico servico = this._serviceOrdemServico.findByServicoOrdemServicoExists(servicoId);

        this._serviceOrdemServico.incluirServico(entity, servico);

        this._serviceOrdemServico.update(entity);

        return ResponseEntity.ok(entity);
    }

    @GetMapping("incluir/itemservico")
    @ApiOperation(value = "Registra nova ordem de serviço com base no Orçamento")
    public ResponseEntity<OrdemServico> incluirItemServico(
            @ApiParam(example = "16082020122006", value = "Código de Identificação: default: "
                    + "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
                    @RequestParam(name = "identificacao") String identificacao,
                    @RequestParam(name = "itemServicoId") String itemServicoId
                    ) {

        OrdemServico entity = this._serviceOrdemServico.findByIdentificacaoEquals(identificacao);

        Produto itemServico = this._serviceOrdemServico.findByItemOrdemServicoExists(itemServicoId);

        this._serviceOrdemServico.incluirItemOrdemServico(entity, itemServico);

        this._serviceOrdemServico.update(entity);

        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("cancelar")
    @ApiOperation(value = "Registra cancelamento do orçamento")
    public ResponseEntity<OrdemServico> cancelar(
            @ApiParam(example = "16082020122006", 
            value = "Código de Identificação: default: " +
            "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
            @RequestParam(name = "identificacao") String identificacao) {

        OrdemServico entity = this._serviceOrdemServico.findByIdentificacaoEquals(identificacao);

        this._serviceOrdemServico.remove(entity.getId());

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    @ApiOperation(value = "Altera _model_  já cadastrado se itens necessários estiverem válidos")
    public ResponseEntity<OrdemServico> update(@RequestBody @Valid OrdemServico entity) {
        _serviceOrdemServico.valida(entity);

        entity.configureServicos();

        entity = this._serviceOrdemServico.update(entity);

        return ResponseEntity.ok(entity);
    }

    @PostMapping("receberPagamento")
    @ApiOperation(value = "Altera _model_  já cadastrado se itens necessários estiverem válidos")
    public ResponseEntity<OrdemServico> receberPagamento(
        @ApiParam(example = "16082020122006", 
            value = "Código de Identificação: default: " +
            "ddMMyyyyHHmmss dd = dia/ MM = mês/ yyyy = Ano/ HH = hora/ mm =Minuto/ ss = Segundo") 
            @RequestParam(name = "identificacao") String identificacao,
            @RequestBody @Valid FinanceiroEntrada entrada) {

        OrdemServico entity = this._serviceOrdemServico.findByIdentificacaoEquals(identificacao);

        this._serviceOrdemServico.receberPagamento(entity, entrada);

        entity = this._serviceOrdemServico.update(entity);

        return ResponseEntity.ok(entity);
    }
}