package com.mecanica.infra.repositorys.ordemServico.orcamento;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.infra.repositorys.ordemServico.AbstractOrdemServicoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrcamentoRepository extends AbstractOrdemServicoRepository<Orcamento> {
	
	Orcamento findByIdentificacaoEquals(String identificacao);

	Page<Orcamento> findAllBySituacaoEquals(EnumSituacaoOrcamento situacao, Pageable paginacao);

}