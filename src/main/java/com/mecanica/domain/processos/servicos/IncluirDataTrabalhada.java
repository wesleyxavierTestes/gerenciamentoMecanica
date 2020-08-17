package com.mecanica.domain.processos.servicos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.diasTrabalhados.DiasTrabalhados;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class IncluirDataTrabalhada extends ServiceProcessos<OrdemServico> {

    public IncluirDataTrabalhada(OrdemServico ordemServico) {
        super(ordemServico);
    }

    public void incluirDataTrabalhada(String data) {
        if (ordemServico.getSituacao() != EnumSituacaoOrdemServico.Executando) {
            throw new RegraBaseException("Verifique o Diagnóstico");
        }

        if (!Objects.nonNull(ordemServico.getDataInicial())) {
            throw new RegraBaseException("Serviço não iniciado");
        }

        if (Objects.nonNull(ordemServico.getDataFinalizacao())) {
            throw new RegraBaseException("Serviço já Finalizado");
        }

        List<DiasTrabalhados> list = ordemServico.getDiasTrabalhados();

        DiasTrabalhados diasTrabalhados = new DiasTrabalhados();
        
        diasTrabalhados.setId(UUID.randomUUID());
        diasTrabalhados.setDia(LocalDateTime.parse(data));

        list.add(diasTrabalhados);

        ordemServico.setDiasTrabalhados(list);
    }

}