package com.mecanica.domain.processos.avaliacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class CancelarOrcamento extends ServiceProcessos<Orcamento>  {

    public CancelarOrcamento(Orcamento ordemServico) {
        super(ordemServico);
    }

    public void cancelarOrcamento() {    
        if (!(ordemServico.getSituacao() == EnumSituacaoOrcamento.Avaliado &&
            ordemServico.getAvaliacao().getDiagnostico() == EnumDiagnosticoAvaliacao.TemConcerto)) {
            throw new RegraBaseException("Verifique o Diagnóstico");
        }

        ordemServico.setSituacao(EnumSituacaoOrcamento.Cancelado);

        ordemServico.setDataFinalizacao(LocalDateTime.now());
        ordemServico.setDataCancelamento(LocalDate.now());
    }

}