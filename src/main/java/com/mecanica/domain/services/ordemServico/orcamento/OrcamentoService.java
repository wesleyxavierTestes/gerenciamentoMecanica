package com.mecanica.domain.services.ordemServico.orcamento;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.ordemServico.orcamento.IOrcamentoRepository;

import org.springframework.stereotype.Service;

@Service
public class OrcamentoService extends BaseService<Orcamento, IOrcamentoRepository> {
    
    public OrcamentoService(IOrcamentoRepository repository) {
        super(repository);
    }
}
