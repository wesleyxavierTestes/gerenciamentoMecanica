package com.mecanica.domain.services.categoria;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.categoria.ICategoriaServicoRepository;
import com.mecanica.domain.entities.categoria.CategoriaServico;

import org.springframework.stereotype.Service;

@Service
public class CategoriaServicoService extends BaseService<CategoriaServico, ICategoriaServicoRepository> {

    public CategoriaServicoService(ICategoriaServicoRepository repository) {
        super(repository);
    }
}