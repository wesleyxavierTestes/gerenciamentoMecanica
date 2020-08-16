package com.mecanica.domain.processos.servicos;

import java.time.LocalDateTime;
import java.util.Set;
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
        if (ordemServico.getSituacao() == EnumSituacaoOrdemServico.Cancelado
                || ordemServico.getSituacao() == EnumSituacaoOrdemServico.Finalizado) {
            throw new RegraBaseException("Verifique o Diagnóstico");
        }

        Set<DiasTrabalhados> list = ordemServico.getDiasTrabalhados();

        DiasTrabalhados diasTrabalhados = new DiasTrabalhados();
        diasTrabalhados.setId(UUID.randomUUID());
        diasTrabalhados.setDataCadastro(LocalDateTime.parse(data));
        diasTrabalhados.setOrdemServico(ordemServico);

        list.add(diasTrabalhados);

        ordemServico.setDiasTrabalhados(list);
    }

}