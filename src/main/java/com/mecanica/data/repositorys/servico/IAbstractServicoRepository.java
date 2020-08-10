package com.mecanica.data.repositorys.servico;

import com.mecanica.data.repositorys.IBaseRepository;
import com.mecanica.domain.entities.servico.AbstractServico;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IAbstractServicoRepository<T extends AbstractServico> extends IBaseRepository<T> {
    
}