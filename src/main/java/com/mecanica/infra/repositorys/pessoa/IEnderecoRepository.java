package com.mecanica.infra.repositorys.pessoa;

import com.mecanica.domain.entities.pessoa.Endereco;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IEnderecoRepository extends IBaseRepository<Endereco> {
}