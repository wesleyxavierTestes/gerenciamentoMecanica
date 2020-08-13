package com.mecanica.domain.processos.servicos;

import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class CriarOrdemServico extends ServiceProcessos<Orcamento>  {

    public CriarOrdemServico(Orcamento ordemServico) {
        super(ordemServico);
    }
}