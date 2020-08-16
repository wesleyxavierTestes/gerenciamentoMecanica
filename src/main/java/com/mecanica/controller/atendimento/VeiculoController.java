package com.mecanica.controller.atendimento;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.application.applicationServices.cliente.ClienteValidations;
import com.mecanica.application.applicationServices.veiculo.VeiculoValidations;
import com.mecanica.controller.BaseController;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.veiculo.Veiculo;

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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController extends BaseController {

    private final VeiculoValidations _service;
    private final VeiculoValidations _serviceVeiculo;
    private final ClienteValidations _serviceCliente;

    @Autowired
    public VeiculoController(VeiculoValidations veiculo, VeiculoValidations serviceVeiculo,
            ClienteValidations serviceCliente) {
        _service = veiculo;
        _serviceCliente = serviceCliente;
        _serviceVeiculo = serviceVeiculo;
    }

    @GetMapping("list/filter")
    @ApiOperation(value = "Lista models mediante paginação " + 
                          "e Filtra mediante parametros do _model_. Default: 10 itens")
    public ResponseEntity<Page<Veiculo>> listFilter(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @RequestBody Veiculo cliente) {

        Page<Veiculo> list = this._service.findAllFilter(cliente, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list")
    @ApiOperation(value = "Lista models mediante paginação. Default: 10 itens")
    public ResponseEntity<Page<Veiculo>> list(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page) {

        Page<Veiculo> list = this._service.findAll(page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/cliente")
    @ApiOperation(value = "Busca todos os veiculos do cliente")
    public ResponseEntity<Page<Veiculo>> listCliente(
            @ApiParam(example = "1", value = "Número pagina para paginação: Mínimo: 1") 
            @RequestParam(name = "page") int page,
            @ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do cliente cadastrado") 
            @RequestParam(name = "clienteId") String clienteId) {

        Page<Veiculo> list = this._service.findAllByClienteId(clienteId, page);

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    @ApiOperation(value = "Busca um único _model_ referente ao específico id")
    public ResponseEntity<Veiculo> find(
            @ApiParam(example = "x67faa25-5a18-43ea-920a-ad3a654a8153", value = "id do _model_ cadastrado") 
            @RequestParam(name = "id") String id) {

        Veiculo entity = this._service.find(UUID.fromString(id));

        return ResponseEntity.ok(entity);
    }

    @PostMapping("save")
    @ApiImplicitParams(value = { 
        @ApiImplicitParam(name = "É necessário Cliente cadastrado"),
        @ApiImplicitParam(name = "Não permitido Veículo com 'nome, marca, ano, cor e renavam' igual") 
        })
    @ApiOperation(value = "Salva _model_ se itens necessários estiverem válidos")
    public ResponseEntity<Veiculo> save(@RequestBody @Valid Veiculo entity) {

        Cliente cliente = _serviceCliente.findValidExistsById(entity.getCliente());

        _service.findValidBusiness(entity);

        entity = _service.save(entity, cliente);

        return ResponseEntity.ok(entity);
    }

    @PutMapping("update")
    @ApiOperation(value = "Altera _model_  já cadastrado se itens necessários estiverem válidos")
    public ResponseEntity<Veiculo> update(@RequestBody @Valid Veiculo entity) {

        Cliente cliente = _serviceCliente.findValidExistsById(entity.getCliente());
        Veiculo entityUpdate = _serviceVeiculo.validaClienteReferenteDoVeiculo(entity, cliente);

        entity = this._service.update(entity, entityUpdate);

        return ResponseEntity.ok(entity);
    }
}