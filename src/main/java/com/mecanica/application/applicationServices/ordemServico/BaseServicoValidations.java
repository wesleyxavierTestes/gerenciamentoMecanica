package com.mecanica.application.applicationServices.ordemServico;

import java.util.Objects;
import java.util.UUID;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.application.applicationServices.cliente.ClienteValidations;
import com.mecanica.application.applicationServices.funcionario.FuncionarioValidations;
import com.mecanica.application.applicationServices.veiculo.VeiculoValidations;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.entities.servico.ServicoOrdemServico;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.BaseService;
import com.mecanica.domain.services.produto.ProdutoService;
import com.mecanica.domain.services.servico.ServicoService;

public class BaseServicoValidations<T extends AbstractOrdemServico, Y extends BaseService<T, ?>> 
extends BaseValidations<T, Y> {
    protected final ServicoService _serviceServico;
    protected final ProdutoService _serviceProduto;
    protected final FuncionarioValidations _serviceFuncionario;
    protected final ClienteValidations _serviceCliente;
    protected final VeiculoValidations _serviceVeiculo;

    public BaseServicoValidations(Y serviceOrdemServico, ServicoService serviceServico,
            ProdutoService serviceProduto, ClienteValidations serviceCliente, VeiculoValidations serviceVeiculo,
            FuncionarioValidations serviceFuncionario) {
        super(serviceOrdemServico);
        _serviceCliente = serviceCliente;
        _serviceVeiculo = serviceVeiculo;
        _serviceFuncionario = serviceFuncionario;
        _serviceServico = serviceServico;
        _serviceProduto = serviceProduto;
    }

    public void valida(OrdemServico entity) {

        IFuncionario atendente = entity.getAtendente();
        atendente = _serviceFuncionario.findValidExistsById((Funcionario) atendente);
        entity.setAtendente(atendente);

        Cliente cliente = entity.getCliente();
        cliente = _serviceCliente.findValidExistsById(cliente);
        entity.setCliente(cliente);

        Veiculo veiculo = entity.getVeiculo();
        veiculo = _serviceVeiculo.findValidExistsById(veiculo);
        entity.setVeiculo(veiculo);

        for (IServico servico : entity.getServicoItens()) {
            if (servico instanceof ServicoOrdemServico)
                this.findByServicoOrdemServicoExists(servico.getId().toString());
            else
                this.findByItemOrdemServicoExists(servico.getId().toString());
        }
    }

    public void remove(UUID id) {
        this._service.remove(id);
    }

    public Produto findByItemOrdemServicoExists(String itemServicoId) {
        Produto entity = this._serviceProduto.find(UUID.fromString(itemServicoId));
        if (!Objects.nonNull(entity))
            throw new ValidacaoControllerBaseException("Item de serviço inexistênte");

        return entity;
    }

    public Servico findByServicoOrdemServicoExists(String servicoId) {
        Servico entity = this._serviceServico.find(UUID.fromString(servicoId));
        if (!Objects.nonNull(entity))
            throw new ValidacaoControllerBaseException("Serviço inexistênte");
        return entity;
    }
}