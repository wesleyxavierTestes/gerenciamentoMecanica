package com.mecanica.domain.services.ordemServico.ordemServico;

import java.util.Objects;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.financeiro.FinanceiroEntrada;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.domain.processos.servicos.CriarOrdemServico;
import com.mecanica.domain.processos.servicos.IncluirDataTrabalhada;
import com.mecanica.domain.processos.servicos.IncluirFinalizacao;
import com.mecanica.domain.processos.servicos.IncluirInicio;
import com.mecanica.domain.processos.servicos.IncluirItemOrdemServico;
import com.mecanica.domain.processos.servicos.IncluirServico;
import com.mecanica.domain.processos.servicos.ReceberPagamento;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.ordemServico.ordemServico.IOrdemServicoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrdemServicoService extends BaseService<OrdemServico, IOrdemServicoRepository> {
    
    public OrdemServicoService(IOrdemServicoRepository repository) {
        super(repository);
    }

    /**
     * Cria uma nova Ordem de serviço mediante orçamento
     * @param entity
     */
	public OrdemServico registrarNovaOrdemServicoViaOrcamento(Orcamento entity) {

        CriarOrdemServico criarOrdemServico = new CriarOrdemServico(entity);

        OrdemServico ordemServico = criarOrdemServico.configurarNovaOrdemServico();

        return ordemServico;
    }
    
        /**
     * Busca Situação do tipo enum, qual no banco é string
     * 
     * @param situacao
     * @param page
     * @return
     */
    public Page<OrdemServico> findAllBySituacaoEquals(EnumSituacaoOrdemServico situacao, int page) {
        PageRequest paginacao = PageRequest.of((page - 1), 10);

        Page<OrdemServico> list = this.repository.findAllBySituacaoEquals(situacao, paginacao);

        return list;
    }

    public OrdemServico save(OrdemServico entity) {
        OrdemServico entityExists = this.findByIdentificacaoEquals(entity.getIdentificacao());
        
        if (Objects.nonNull(entityExists))
            throw new RegraBaseException("Ordem de serviço duplicada");

        entity = super.save(entity);
        
        return entity;
    }

    public OrdemServico findByIdentificacaoEquals(String identificacao) {
        OrdemServico entity = this.repository.findByIdentificacaoEquals(identificacao);

        return entity;
    }
	
    /**
     * Pesquisa por nome ou cpf ou cnpj do cliente caso não for pesquisa por nome,
     * coloca-se caracteres inválido no nome para ignorar pesquisa
     * 
     * @param clienteNome
     * @param clienteCpfCnpj
     * @param page
     * @return
     */
    public Page<OrdemServico> findAllByClienteIdOrNomeOrCpfOrCnpj(String clienteNome, String clienteCpfCnpj, int page) {

        if (!clienteCpfCnpj.isEmpty())
            clienteNome = ".##@$@$";

        Page<OrdemServico> orcamentos = this.repository
                .findAllByClienteNomeContainingIgnoreCaseOrClienteCpfEqualsOrClienteCnpjEquals(clienteNome,
                        clienteCpfCnpj, clienteCpfCnpj, PageRequest.of((page - 1), 10));
        return orcamentos;
    }

	public OrdemServico incluirItemOrdemServico(OrdemServico entity, Produto itemServico) {
        IncluirItemOrdemServico incluirItemOrdemServico = new IncluirItemOrdemServico(entity);

        incluirItemOrdemServico.incluirItemOrdemServico(itemServico);
        
		return incluirItemOrdemServico.getordemServico();
	}

	public OrdemServico incluirDataTrabalhada(OrdemServico entity, String data) {
        IncluirDataTrabalhada incluirDataTrabalhada = new IncluirDataTrabalhada(entity);

        incluirDataTrabalhada.incluirDataTrabalhada(data);

		return incluirDataTrabalhada.getordemServico();
	}

	public OrdemServico incluirServico(OrdemServico entity, Servico servico) {
        IncluirServico incluirServico = new IncluirServico(entity);

        incluirServico.incluirServico(servico);

		return incluirServico.getordemServico();
	}

	public OrdemServico incluirInicio(OrdemServico entity, String data) {
		IncluirInicio incluirInicio = new IncluirInicio(entity);

        incluirInicio.incluirInicio(data);

		return incluirInicio.getordemServico();
	}

	public OrdemServico incluirFinalizacao(OrdemServico entity, String data) {
		IncluirFinalizacao incluirFinalizacao = new IncluirFinalizacao(entity);

        incluirFinalizacao.incluirFinalizacao(data);

		return incluirFinalizacao.getordemServico();
	}

	public OrdemServico receberPagamento(OrdemServico entity, FinanceiroEntrada entrada) {
		ReceberPagamento receberPagamento = new ReceberPagamento(entity);

        receberPagamento.receberPagamento(entrada);

		return receberPagamento.getordemServico();
	}
}