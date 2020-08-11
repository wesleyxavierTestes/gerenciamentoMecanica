package com.mecanica.infra.repositorys.ordemServico.ordemServico;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IOrdemServicoRepository extends IBaseRepository<OrdemServico> {

}