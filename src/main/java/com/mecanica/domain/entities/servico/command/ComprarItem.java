package com.mecanica.domain.entities.servico.command;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;

public class ComprarItem extends ServiceCommand<OrdemServico> {

    public ComprarItem(OrdemServico ordemServico) {
        super(ordemServico);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean Acao(OrdemServico ordemServico) {
        // TODO Auto-generated method stub
        return false;
    }

}