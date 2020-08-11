package com.mecanica.domain.services.produto;

import java.util.UUID;

import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.produto.IProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends BaseService<Produto, IProdutoRepository> {

    @Autowired
    public ProdutoService(IProdutoRepository repository) {
        super(repository);
    }

    @Override
    public Produto save(Produto entity) {
        entity.setCodigo(UUID.randomUUID());
        
        entity = super.save(entity);

        return entity;
    }

    @Override
    public Produto update(Produto entity) {

        entity = super.save(entity);

        return entity;
    }
}