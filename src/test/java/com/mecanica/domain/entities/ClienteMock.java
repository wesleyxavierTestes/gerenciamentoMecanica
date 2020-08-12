package com.mecanica.domain.entities;

import com.mecanica.domain.entities.cliente.Cliente;

public class ClienteMock {
    
    public static Cliente Mock() {
        Cliente cliente = new Cliente();
        cliente.setNome("Marcelo");

        return cliente;
    }
}