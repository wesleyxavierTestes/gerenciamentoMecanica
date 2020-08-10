package com.mecanica.domain.services.servico.ordemServico;

import com.mecanica.data.repositorys.servico.ordemServico.IOrdemServicoRepository;
import com.mecanica.domain.entities.servico.ordemServico.OrdemServico;
import com.mecanica.domain.services.BaseService;

import org.springframework.stereotype.Service;

@Service
public class OrdemServicoService extends BaseService<OrdemServico, IOrdemServicoRepository> {
    
    public OrdemServicoService(IOrdemServicoRepository repository) {
        super(repository);
    }
}