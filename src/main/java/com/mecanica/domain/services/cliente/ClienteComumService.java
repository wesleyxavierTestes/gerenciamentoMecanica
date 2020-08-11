
package com.mecanica.domain.services.cliente;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.cliente.IClienteComumRepository;
import com.mecanica.domain.entities.cliente.ClienteComum;

import org.springframework.stereotype.Service;

@Service
public class ClienteComumService extends BaseService<ClienteComum, IClienteComumRepository> {

    public ClienteComumService(IClienteComumRepository repository) {
        super(repository);
    }
}