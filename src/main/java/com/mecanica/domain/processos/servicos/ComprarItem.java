package com.mecanica.domain.processos.servicos;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class ComprarItem extends ServiceProcessos<OrdemServico> {

    public ComprarItem(OrdemServico ordemServico) {
        super(ordemServico);
    }

}