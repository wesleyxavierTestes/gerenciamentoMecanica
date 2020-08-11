package com.mecanica.domain.services.estoque;

import com.mecanica.domain.entities.estoque.AbstractEstoque;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.estoque.IEstoqueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService extends BaseService<AbstractEstoque, IEstoqueRepository> {
	
    @Autowired
    public EstoqueService(final IEstoqueRepository repository) {
        super(repository);
    }
}