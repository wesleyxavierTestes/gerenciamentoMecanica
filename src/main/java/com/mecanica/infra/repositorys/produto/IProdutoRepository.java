package com.mecanica.infra.repositorys.produto;

import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IProdutoRepository extends IBaseRepository<Produto> {
    
}