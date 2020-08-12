package com.mecanica.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mecanica.domain.entities.ClienteMock;
import com.mecanica.domain.entities.Veiculomock;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.ordemServico.orcamento.OrcamentoService;

import org.junit.jupiter.api.Test;

public class OrcamentoControllerTest {

    private OrcamentoService orcamentoService = new OrcamentoService(null);

    @Test
    public void criarPedidoAvaliacaoTest() {
        String causas = "Som de Batidas no motor";

        Cliente cliente = ClienteMock.Mock();
        Veiculo veiculo = Veiculomock.mock();
        
        assertTrue(true);
        
        Orcamento orcamento = this.orcamentoService.criarPedidoAvaliacao(new Funcionario(), cliente, veiculo, causas);

        assertNotNull(orcamento);
    }

}