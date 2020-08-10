package com.mecanica.data.repositorys.cliente;

import com.mecanica.data.repositorys.IBaseRepository;
import com.mecanica.domain.entities.cliente.ClienteEmpresa;

import org.springframework.stereotype.Repository;

@Repository
public interface IClienteEmpresaRepository extends IBaseRepository<ClienteEmpresa> {
}