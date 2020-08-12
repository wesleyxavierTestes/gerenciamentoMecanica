package com.mecanica.domain.services.produto;

import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.infra.repositorys.produto.IProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends AbstractProdutoService<Produto, IProdutoRepository> {

    @Autowired
    public ProdutoService(IProdutoRepository repository) {
        super(repository);
    }
}