package com.mecanica.domain.processos.servicos;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class InformarFinalizacao extends ServiceProcessos<OrdemServico> {

    public InformarFinalizacao(OrdemServico ordemServico) {
        super(ordemServico);
    }

}