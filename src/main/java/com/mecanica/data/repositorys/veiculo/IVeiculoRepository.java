package com.mecanica.data.repositorys.veiculo;

import com.mecanica.data.repositorys.IBaseRepository;
import com.mecanica.domain.entities.veiculo.Veiculo;

import org.springframework.stereotype.Repository;

@Repository
public interface IVeiculoRepository extends IBaseRepository<Veiculo> {
}