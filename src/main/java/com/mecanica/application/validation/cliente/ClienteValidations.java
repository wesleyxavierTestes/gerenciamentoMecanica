package com.mecanica.application.validation.cliente;

import com.mecanica.application.validation.BaseValidations;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.services.cliente.ClienteService;

import org.springframework.stereotype.Service;

@Service
public class ClienteValidations extends BaseValidations<Cliente, ClienteService> {

    public ClienteValidations(ClienteService serviceCliente) {
        super(serviceCliente);
    }

    @Override
    public String getNome() {
        return "Cliente";
    }
}