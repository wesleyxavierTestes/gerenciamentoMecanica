package com.mecanica.domain.services.categoria;

import com.mecanica.domain.services.BaseService;
import com.mecanica.data.repositorys.categoria.ICategoriaRepository;
import com.mecanica.domain.entities.categoria.Categoria;

import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends BaseService<Categoria, ICategoriaRepository> {

    public CategoriaService(ICategoriaRepository repository) {
        super(repository);
    }
}