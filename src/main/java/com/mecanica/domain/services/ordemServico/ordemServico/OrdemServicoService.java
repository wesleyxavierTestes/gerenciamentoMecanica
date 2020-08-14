package com.mecanica.domain.services.ordemServico.ordemServico;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.processos.servicos.CriarOrdemServico;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.ordemServico.ordemServico.IOrdemServicoRepository;

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
}