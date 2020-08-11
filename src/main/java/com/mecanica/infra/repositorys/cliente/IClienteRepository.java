
package com.mecanica.infra.repositorys.cliente;

import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends IBaseRepository<Cliente> {

}