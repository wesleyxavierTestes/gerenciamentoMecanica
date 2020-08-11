package com.mecanica.infra.repositorys.estoque;

import com.mecanica.domain.entities.estoque.EstoqueSaida;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IEstoqueSaidaRepository extends IBaseRepository<EstoqueSaida>{
    
}