package com.mecanica.infra.repositorys.ordemServico.orcamento;

import java.util.List;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.infra.repositorys.ordemServico.AbstractOrdemServicoRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrcamentoRepository extends AbstractOrdemServicoRepository<Orcamento> {

	@Query(
		nativeQuery = true,
		value = "select o from orcamento o where o.situacao = ?1 limit 1"
	)
	Orcamento findByIdentificacao(String identificacao);

}