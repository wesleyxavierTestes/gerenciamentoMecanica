package com.mecanica.infra.repositorys.ordemServico.ordemServico;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.infra.repositorys.ordemServico.AbstractOrdemServicoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdemServicoRepository extends AbstractOrdemServicoRepository<OrdemServico> {

	Page<OrdemServico> findAllBySituacaoEquals(EnumSituacaoOrdemServico situacao, Pageable paginacao);
}