package com.mecanica.data.repositorys.servico.orcamento;

import com.mecanica.data.repositorys.servico.IAbstractServicoRepository;
import com.mecanica.domain.entities.servico.orcamento.Orcamento;

import org.springframework.stereotype.Repository;

@Repository
public interface IOrcamentoRepository extends IAbstractServicoRepository<Orcamento> {

}