package com.mecanica.infra.repositorys.servico;

import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IServicoRepository extends IBaseRepository<Servico> {
}