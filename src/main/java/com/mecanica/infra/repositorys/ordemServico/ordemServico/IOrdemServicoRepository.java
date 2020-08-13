package com.mecanica.infra.repositorys.ordemServico.ordemServico;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.infra.repositorys.ordemServico.AbstractOrdemServicoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IOrdemServicoRepository extends AbstractOrdemServicoRepository<OrdemServico> {

}