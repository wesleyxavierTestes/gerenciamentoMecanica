package com.mecanica.infra.repositorys.veiculo;
import java.util.UUID;

import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IVeiculoRepository extends IBaseRepository<Veiculo> {

	Veiculo findByRenavam(String veiculoRenavam);

	@Query(
		value = "select v from Veiculo as v where v.cliente.id = ?1",
		countQuery = "select v from Veiculo as v where v.cliente.id = ?1"
	)
	Page<Veiculo> findAllByClienteId(UUID clienteId, PageRequest of);
}