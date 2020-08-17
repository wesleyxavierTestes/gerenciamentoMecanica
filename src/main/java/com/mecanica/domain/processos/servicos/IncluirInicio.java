package com.mecanica.domain.processos.servicos;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class IncluirInicio extends ServiceProcessos<OrdemServico> {

    public IncluirInicio(OrdemServico ordemServico) {
        super(ordemServico);
    }

    public void incluirInicio(String data) {
        if (ordemServico.getSituacao() != EnumSituacaoOrdemServico.Aguardando) {
            throw new RegraBaseException("Verifique o Diagnóstico");
        }
        
        if (Objects.nonNull(ordemServico.getDataInicial())) {
            throw new RegraBaseException("Serviço já iniciado");
        } 
        
        if (Objects.nonNull(ordemServico.getDataFinalizacao())) {
            throw new RegraBaseException("Serviço já Finalizado");
        }

        ordemServico.setDataInicial(LocalDateTime.parse(data));
        ordemServico.setSituacao(EnumSituacaoOrdemServico.Executando);
    }
}