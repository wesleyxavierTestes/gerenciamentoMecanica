package com.mecanica.infra.repositorys.estoque;

import org.springframework.stereotype.Repository;

import com.mecanica.domain.entities.estoque.AbstractEstoque;
import com.mecanica.infra.repositorys.IBaseRepository;

@Repository
public interface IEstoqueRepository extends IBaseRepository<AbstractEstoque> {

}