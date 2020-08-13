package com.mecanica.domain.processos.avaliacao;

import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.cliente.ClienteHistoricoRetorno;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.enuns.EnumTipoPedido;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class InformarOrcamentoCliente extends ServiceProcessos<Orcamento> {

    public InformarOrcamentoCliente(Orcamento ordemServico) {
        super(ordemServico);
    }

    public ClienteHistoricoRetorno incluirOrcamento(Funcionario atendente,
            ClienteHistoricoRetorno clienteHistoricoRetorno) {
        Cliente cliente = ordemServico.getCliente();

        clienteHistoricoRetorno.setCliente(cliente);
        clienteHistoricoRetorno.setContactor(atendente);
        clienteHistoricoRetorno.setTipoPedido(EnumTipoPedido.Orcamento);

        return clienteHistoricoRetorno;
    }
}