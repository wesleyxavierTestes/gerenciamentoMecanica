package com.mecanica.infra.repositorys.categoria;

import java.util.List;

import com.mecanica.domain.entities.categoria.CategoriaServico;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaServicoRepository extends IBaseRepository<CategoriaServico> {
    List<CategoriaServico> findByNome(String nome);
}