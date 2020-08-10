package com.mecanica.data.repositorys.pessoa;

import com.mecanica.data.repositorys.IBaseRepository;
import com.mecanica.domain.entities.pessoa.Endereco;

import org.springframework.stereotype.Repository;

@Repository
public interface IEnderecoRepository extends IBaseRepository<Endereco> {
}