package com.mecanica.domain.services.servico;

import com.mecanica.data.repositorys.servico.IServicoRepository;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.services.BaseService;

import org.springframework.stereotype.Service;

@Service
public class ServicoService extends BaseService<Servico, IServicoRepository> {

    public ServicoService(final IServicoRepository repository) {
        super(repository);
    }
}