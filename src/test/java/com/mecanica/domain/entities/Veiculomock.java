package com.mecanica.domain.entities;

import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.veiculo.Veiculo;

public class Veiculomock {

    public static Veiculo mock() {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Fiat");
        veiculo.setPlaca("brc-1313");
        veiculo.setAno("1999");
        return veiculo;
    }

    public static Veiculo mock(Cliente cliente) {
        Veiculo veiculo = Veiculomock.mock();
        veiculo.setCliente(cliente);

        return veiculo;
    }
    
}