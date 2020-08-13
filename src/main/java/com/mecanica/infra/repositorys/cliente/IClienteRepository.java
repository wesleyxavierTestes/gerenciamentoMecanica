
package com.mecanica.infra.repositorys.cliente;

import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends IBaseRepository<Cliente> {
    @Query(
        value = "select c from #{#entityName} c where lower(c.nome) like %?1%",
        countQuery = "select c from #{#entityName} c where lower(c.nome) like %?1%"
    )
    Page<Cliente> findAllByNomeContains(String nome, PageRequest pge);
}