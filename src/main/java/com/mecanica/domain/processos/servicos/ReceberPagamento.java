package com.mecanica.domain.processos.servicos;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class ReceberPagamento extends ServiceProcessos<OrdemServico> {

    public ReceberPagamento(OrdemServico ordemServico) {
        super(ordemServico);
    }

}