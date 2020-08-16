package com.mecanica.domain.processos.servicos;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class IncluirFinalizacao extends ServiceProcessos<OrdemServico> {

    public IncluirFinalizacao(OrdemServico ordemServico) {
        super(ordemServico);
    }

    public void incluirFinalizacao(String data) {
        if (!Objects.nonNull(ordemServico.getDataInicial())) {
            throw new RegraBaseException("Serviço não iniciado");
        }

        if (Objects.nonNull(ordemServico.getDataFinalizacao())) {
            throw new RegraBaseException("Serviço já Finalizado");
        }

        ordemServico.setDataFinalizacao(LocalDateTime.parse(data));
    }
}