package com.mecanica.infra.repositorys.ordemServico;

import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractOrdemServicoRepository<T extends AbstractOrdemServico>  extends IBaseRepository<T>  {
    Page<T> findAllBySituacao(String situacao, PageRequest of);
}