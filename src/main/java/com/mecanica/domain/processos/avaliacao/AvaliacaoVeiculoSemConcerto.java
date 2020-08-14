package com.mecanica.domain.processos.avaliacao;

import java.time.LocalDateTime;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class AvaliacaoVeiculoSemConcerto extends ServiceProcessos<Orcamento> {

    public AvaliacaoVeiculoSemConcerto(Orcamento ordemServico) {
        super(ordemServico);
    }

    public void veiculoSemConcerto(Mecanico mecanico) {
        LocalDateTime finalizacao = LocalDateTime.now();
        if (ordemServico.getSituacao() != EnumSituacaoOrcamento.Aguardando) {
            throw new RegraBaseException("Orçamento Inválido, verifique a situação");
        }

        ordemServico.setDataFinalizacao(finalizacao);

        Avaliacao avaliacao = ordemServico.getAvaliacao();
        avaliacao.setDataFinalizacao(finalizacao);
        avaliacao.setDiagnostico(EnumDiagnosticoAvaliacao.SemConcerto);
        avaliacao.setMecanico(mecanico);

        ordemServico.setAvaliacao(avaliacao);
        ordemServico.setSituacao(EnumSituacaoOrcamento.SemConserto);
    }
}