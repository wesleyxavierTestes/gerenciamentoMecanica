package com.mecanica.infra.repositorys.ordemServico;

import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractOrdemServicoRepository<T extends AbstractOrdemServico> extends IBaseRepository<T> {

    @Query(
        value = "select v from #{#entityName} as v " +
                "where v.situacao like ?1 ",
		countQuery = "select v from #{#entityName} as v " +
                    "where v.situacao like ?1 "
	)
    Page<T> findAllBySituacao(Object situacao, PageRequest of);

    @Query(
        value = "select v from #{#entityName} as v " +
                "where v.cliente.nome like %?1% " +
                "or (v.cliente.cpf like ?2 and v.cliente.cpf is not null)" +
                "or (v.cliente.cnpj like ?2 and v.cliente.cnpj is not null) ",
		countQuery = "select v from #{#entityName} as v " +
                    "where v.cliente.nome like %?1% " +
                    "or (v.cliente.cpf like ?2 and v.cliente.cpf is not null)" +
                    "or (v.cliente.cnpj like ?2 and v.cliente.cnpj is not null) "
	)
	Page<T> findAllByClienteIdOrNomeOrCpfOrCnpj(String nome, String cpfCnpj, PageRequest of);
}