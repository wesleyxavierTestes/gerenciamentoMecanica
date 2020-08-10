package com.mecanica.domain.services.servico.orcamento;

import com.mecanica.data.repositorys.servico.orcamento.IOrcamentoRepository;
import com.mecanica.domain.entities.servico.orcamento.Orcamento;
import com.mecanica.domain.services.BaseService;

import org.springframework.stereotype.Service;

@Service
public class OrcamentoService extends BaseService<Orcamento, IOrcamentoRepository> {
    
    public OrcamentoService(IOrcamentoRepository repository) {
        super(repository);
    }
}
