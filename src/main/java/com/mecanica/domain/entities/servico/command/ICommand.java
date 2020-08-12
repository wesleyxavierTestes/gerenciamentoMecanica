package com.mecanica.domain.entities.servico.command;

import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;

public interface ICommand {
    
    <T extends AbstractOrdemServico> boolean Acao(T ordemServico);
}