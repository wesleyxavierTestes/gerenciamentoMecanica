
package com.mecanica.infra.repositorys.categoria;

import java.util.List;

import com.mecanica.domain.entities.categoria.CategoriaProduto;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaProdutoRepository extends IBaseRepository<CategoriaProduto> {
    @Query(
        value = "select c from CategoriaProduto c where c.nome like %?1%",
        countQuery = "select c from CategoriaProduto c where c.nome like %?1%"
    )
    Page<CategoriaProduto> findByNome(String nome, PageRequest pge);
}