
package com.mecanica.infra.repositorys.categoria;

import com.mecanica.domain.entities.categoria.ICategoria;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ICategoriaRepository<T extends ICategoria> extends IBaseRepository<T> {

    /**
     * Busca
     * @param nome
     * @param pge
     * @return
     */
    Page<T> findAllByNomeContainingIgnoreCase(String nome, Pageable pge);
}