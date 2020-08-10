package com.mecanica.data.repositorys.categoria;

import com.mecanica.data.repositorys.IBaseRepository;
import com.mecanica.domain.entities.categoria.Categoria;

import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends IBaseRepository<Categoria> {
}