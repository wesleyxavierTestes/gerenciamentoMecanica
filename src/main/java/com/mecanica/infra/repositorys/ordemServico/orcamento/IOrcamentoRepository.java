package com.mecanica.infra.repositorys.ordemServico.orcamento;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.infra.repositorys.ordemServico.AbstractOrdemServicoRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrcamentoRepository extends AbstractOrdemServicoRepository<Orcamento> {

	@Query(
		value = "select o from Orcamento o where o.identificacao like ?1"
	)
	Orcamento findByIdentificacao(String identificacao);

}