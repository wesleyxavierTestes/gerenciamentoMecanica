package com.mecanica.application.applicationServices.ordemServico;

import com.mecanica.application.applicationServices.cliente.ClienteValidations;
import com.mecanica.application.applicationServices.funcionario.FuncionarioValidations;
import com.mecanica.application.applicationServices.veiculo.VeiculoValidations;
import com.mecanica.domain.entities.financeiro.FinanceiroEntrada;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.services.ordemServico.ordemServico.OrdemServicoService;
import com.mecanica.domain.services.produto.ProdutoService;
import com.mecanica.domain.services.servico.ServicoService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class OrdemServicoValidations extends BaseServicoValidations<OrdemServico, OrdemServicoService> {


    public OrdemServicoValidations(OrdemServicoService serviceOrdemServico, ServicoService serviceServico,
            ProdutoService serviceProduto, ClienteValidations serviceCliente, VeiculoValidations serviceVeiculo,
            FuncionarioValidations serviceFuncionario) {
                super(serviceOrdemServico, serviceServico, serviceProduto, serviceCliente, serviceVeiculo, serviceFuncionario);
    }


    public Page<OrdemServico> findAllByClienteIdOrNomeOrCpfOrCnpj(String clienteNome, String clienteCpfCnpj, int page) {
        return this._service.findAllByClienteIdOrNomeOrCpfOrCnpj(clienteNome, clienteCpfCnpj, page);
    }

    public OrdemServico findByIdentificacaoEquals(String identificacao) {
        return this._service.findByIdentificacaoEquals(identificacao);
    }

    public OrdemServico incluirItemOrdemServico(OrdemServico entity, Produto itemServico) {
        return this._service.incluirItemOrdemServico(entity, itemServico);
    }

    public OrdemServico incluirServico(OrdemServico entity, Servico servico) {
        return this._service.incluirServico(entity, servico);
    }

    public OrdemServico incluirDataTrabalhada(OrdemServico entity, String data) {
        return this._service.incluirDataTrabalhada(entity, data);
    }


	public OrdemServico incluirInicio(OrdemServico entity, String data) {
        return this._service.incluirInicio(entity, data);
	}


	public OrdemServico incluirFinalizacao(OrdemServico entity, String data) {
        return this._service.incluirFinalizacao(entity, data);
	}


	public OrdemServico receberPagamento(OrdemServico entity, FinanceiroEntrada entrada) {
        return this._service.receberPagamento(entity, entrada);
	}
}