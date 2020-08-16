package com.mecanica.domain.processos.servicos;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class IncluirInicio extends ServiceProcessos<OrdemServico> {

    public IncluirInicio(OrdemServico ordemServico) {
        super(ordemServico);
    }

    public void incluirInicio(String data) {
        if (Objects.nonNull(ordemServico.getDataInicial())) {
            throw new RegraBaseException("Serviço já iniciado");
        }

        ordemServico.setDataInicial(LocalDateTime.parse(data));
    }
}