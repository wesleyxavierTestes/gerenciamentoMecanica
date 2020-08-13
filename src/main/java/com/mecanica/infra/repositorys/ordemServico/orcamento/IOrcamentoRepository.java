package com.mecanica.infra.repositorys.ordemServico.orcamento;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.infra.repositorys.ordemServico.AbstractOrdemServicoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IOrcamentoRepository extends AbstractOrdemServicoRepository<Orcamento> {

	Orcamento findByIdentificacao(String identificacao);

}