
package com.mecanica.infra.repositorys.categoria;

import com.mecanica.domain.entities.categoria.ICategoria;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ICategoriaRepository<T extends ICategoria> extends IBaseRepository<T> {

    /**
     * Busca
     * @param nome
     * @param pge
     * @return
     */
    @Query(
        value = "select c from #{#entityName} c where LOWER(c.nome) LIKE LOWER(concat('%', concat(?1, '%')))",
        countQuery = "select c from #{#entityName} c where LOWER(c.nome) LIKE LOWER(concat('%', concat(?1, '%')))"
    )
    Page<T> findByNomeContainingIgnoreCase(String nome, PageRequest pge);
}