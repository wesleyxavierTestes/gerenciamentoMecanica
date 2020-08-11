package com.mecanica.infra.repositorys.cliente;

import com.mecanica.domain.entities.cliente.ClienteEmpresa;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IClienteEmpresaRepository extends IBaseRepository<ClienteEmpresa> {
}