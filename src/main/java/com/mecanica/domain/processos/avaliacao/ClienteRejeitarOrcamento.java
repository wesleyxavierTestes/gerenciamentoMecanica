package com.mecanica.domain.processos.avaliacao;

import java.time.LocalDateTime;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class ClienteRejeitarOrcamento extends ServiceProcessos<Orcamento>  {

    public ClienteRejeitarOrcamento(Orcamento ordemServico) {
        super(ordemServico);
    }

    public void rejeitarOrcamento() {    
        if (ordemServico.getAvaliacao().getDiagnostico() != EnumDiagnosticoAvaliacao.TemConcerto) {
            throw new RegraBaseException("Verifique o Diagnóstico");
        }

        ordemServico.setSituacao(EnumSituacaoOrcamento.Rejeitado);

        ordemServico.setDataFinalizacao(LocalDateTime.now());
    }

}