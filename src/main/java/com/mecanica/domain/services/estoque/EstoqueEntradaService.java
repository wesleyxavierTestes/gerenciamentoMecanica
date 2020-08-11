package com.mecanica.domain.services.estoque;

import com.mecanica.domain.entities.estoque.EstoqueEntrada;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.estoque.IEstoqueEntradaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueEntradaService extends BaseService<EstoqueEntrada, IEstoqueEntradaRepository> {
	
    @Autowired
    public EstoqueEntradaService(final IEstoqueEntradaRepository repository) {
        super(repository);
    }
}