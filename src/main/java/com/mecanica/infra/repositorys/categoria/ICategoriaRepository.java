
package com.mecanica.infra.repositorys.categoria;

import com.mecanica.domain.entities.categoria.ICategoria;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ICategoriaRepository<T extends ICategoria> extends IBaseRepository<T> {
    @Query(
        value = "select c from #{#entityName} c where lower(c.nome) like %?1%",
        countQuery = "select c from #{#entityName} c where lower(c.nome) like %?1%"
    )
    Page<T> findAllByNomeContains(String nome, PageRequest pge);
    
}