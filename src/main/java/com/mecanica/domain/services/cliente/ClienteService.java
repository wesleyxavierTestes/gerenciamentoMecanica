
package com.mecanica.domain.services.cliente;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.cliente.IClienteRepository;
import com.mecanica.domain.entities.cliente.Cliente;

import org.springframework.stereotype.Service;

@Service
public class ClienteService extends BaseService<Cliente, IClienteRepository> {

    public ClienteService(IClienteRepository repository) {
        super(repository);
    }
}