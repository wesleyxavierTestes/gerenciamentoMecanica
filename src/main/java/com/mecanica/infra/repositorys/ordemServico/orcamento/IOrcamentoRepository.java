package com.mecanica.infra.repositorys.ordemServico.orcamento;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IOrcamentoRepository extends IBaseRepository<Orcamento> {

}