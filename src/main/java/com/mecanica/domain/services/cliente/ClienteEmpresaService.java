package com.mecanica.domain.services.cliente;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.cliente.IClienteEmpresaRepository;
import com.mecanica.domain.entities.cliente.ClienteEmpresa;

import org.springframework.stereotype.Service;

@Service
public class ClienteEmpresaService extends BaseService<ClienteEmpresa, IClienteEmpresaRepository> {

    public ClienteEmpresaService(IClienteEmpresaRepository repository) {
        super(repository);
    }
}