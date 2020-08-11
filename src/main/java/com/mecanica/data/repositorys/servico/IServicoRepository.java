package com.mecanica.data.repositorys.servico;

import com.mecanica.data.repositorys.IBaseRepository;
import com.mecanica.domain.entities.servico.Servico;

import org.springframework.stereotype.Repository;

@Repository
public interface IServicoRepository extends IBaseRepository<Servico> {
}