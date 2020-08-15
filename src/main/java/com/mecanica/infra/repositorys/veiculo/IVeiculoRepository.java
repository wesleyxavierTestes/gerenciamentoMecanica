package com.mecanica.infra.repositorys.veiculo;
import java.util.UUID;

import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface IVeiculoRepository extends IBaseRepository<Veiculo> {

	Veiculo findByRenavamEquals(String veiculoRenavam);

	Page<Veiculo> findAllByClienteId(UUID clienteId, Pageable of);
}