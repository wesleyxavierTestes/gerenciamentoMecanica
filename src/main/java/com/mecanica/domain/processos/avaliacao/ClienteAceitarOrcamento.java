package com.mecanica.domain.processos.avaliacao;

import java.time.LocalDateTime;
import java.util.List;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.ServicoOrcamento;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class ClienteAceitarOrcamento extends ServiceProcessos<Orcamento>  {

    public ClienteAceitarOrcamento(Orcamento ordemServico) {
        super(ordemServico);
    }

    public void aceitarOrcamento() {

        if (ordemServico.getAvaliacao().getDiagnostico() != EnumDiagnosticoAvaliacao.TemConcerto) {
            throw new RegraBaseException("Orçamento inválido, verifique o Diagnóstico");
        }

        ordemServico.setSituacao(EnumSituacaoOrcamento.Aceito);

        List<IServico> servicos = ordemServico.getServicoItens();
        for (IServico servico : servicos) {
            ((ServicoOrcamento) servico).setSituacao(EnumSituacaoOrcamento.Aceito);
        }

        ordemServico.setServicoItens(servicos);

        ordemServico.setDataFinalizacao(LocalDateTime.now());
    }

}