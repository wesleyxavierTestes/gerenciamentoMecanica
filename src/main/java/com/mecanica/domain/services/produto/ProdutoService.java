package com.mecanica.domain.services.produto;

import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.infra.repositorys.produto.IProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends AbstractProdutoService<Produto, IProdutoRepository> {

    @Autowired
    public ProdutoService(IProdutoRepository repository) {
        super(repository);
    }

    public Page<Produto> findAllByCountEstoqueZero(int page) {
        Page<Produto> findAllByCountEstoqueZero = this.repository.findAllByCountEstoqueZero(PageRequest.of((page - 1), 10));
        return findAllByCountEstoqueZero;
    }

    public Page<Produto> findAllByCountEstoque(int page) {
        Page<Produto> findAllByCountEstoque = this.repository.findAllByCountEstoque(PageRequest.of((page - 1), 10));
        return findAllByCountEstoque;
    }
}