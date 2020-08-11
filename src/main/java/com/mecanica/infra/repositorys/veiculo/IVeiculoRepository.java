package com.mecanica.infra.repositorys.veiculo;

import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IVeiculoRepository extends IBaseRepository<Veiculo> {
}