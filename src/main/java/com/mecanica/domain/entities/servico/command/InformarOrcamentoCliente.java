package com.mecanica.domain.entities.servico.command;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;

public class InformarOrcamentoCliente extends Command<Orcamento>  {

    public InformarOrcamentoCliente(Orcamento ordemServico) {
        super(ordemServico);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean Acao(Orcamento ordemServico) {
        // TODO Auto-generated method stub
        return false;
    }

}