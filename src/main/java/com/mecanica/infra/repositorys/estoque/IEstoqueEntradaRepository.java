package com.mecanica.infra.repositorys.estoque;

import com.mecanica.domain.entities.estoque.EstoqueEntrada;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IEstoqueEntradaRepository extends IBaseRepository<EstoqueEntrada>{
    
}