package com.mecanica.domain.services.estoque;

import com.mecanica.domain.entities.estoque.EstoqueSaida;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.estoque.IEstoqueSaidaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueSaidaService extends BaseService<EstoqueSaida, IEstoqueSaidaRepository> {
	
    @Autowired
    public EstoqueSaidaService(final IEstoqueSaidaRepository repository) {
        super(repository);
    }
}