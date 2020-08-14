package com.mecanica.infra.repositorys.servico;

import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicoRepository extends IBaseRepository<Servico> {
    @Query(
        value = "select c from #{#entityName} c where lower(c.nome) like %?1%",
        countQuery = "select c from #{#entityName} c where lower(c.nome) like %?1%"
    )
    Page<Servico> findAllByNomeContains(String nome, PageRequest pge);

	Servico findByNomeIgnoreCase(String nome);
}