package com.mecanica.domain.processos.avaliacao;

import java.time.LocalDateTime;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class ClienteAceitarOrcamento extends ServiceProcessos<Orcamento>  {

    public ClienteAceitarOrcamento(Orcamento ordemServico) {
        super(ordemServico);
    }

    public void aceitarOrcamento() {

        if (ordemServico.getSituacao() == EnumSituacaoOrcamento.Aceito ||
            ordemServico.getSituacao() != EnumSituacaoOrcamento.Avaliado ||
            ordemServico.getAvaliacao().getDiagnostico() != EnumDiagnosticoAvaliacao.TemConcerto) {
            throw new RegraBaseException("Verifique o Diagnóstico");
        }

        ordemServico.setSituacao(EnumSituacaoOrcamento.Aceito);

        ordemServico.setDataFinalizacao(LocalDateTime.now());
    }

}