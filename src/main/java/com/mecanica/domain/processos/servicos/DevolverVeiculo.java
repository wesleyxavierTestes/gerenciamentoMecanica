package com.mecanica.domain.processos.servicos;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class DevolverVeiculo extends ServiceProcessos<OrdemServico> {

    public DevolverVeiculo(OrdemServico ordemServico) {
        super(ordemServico);
    }

}