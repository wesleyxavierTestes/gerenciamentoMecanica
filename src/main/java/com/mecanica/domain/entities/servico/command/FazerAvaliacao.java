package com.mecanica.domain.entities.servico.command;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;

public class FazerAvaliacao extends Command<Orcamento>  {

    public FazerAvaliacao(Orcamento ordemServico) {
        super(ordemServico);
    }

    @Override
    public boolean Acao(Orcamento ordemServico) {
        // TODO Auto-generated method stub
        return false;
    }

    
    
}