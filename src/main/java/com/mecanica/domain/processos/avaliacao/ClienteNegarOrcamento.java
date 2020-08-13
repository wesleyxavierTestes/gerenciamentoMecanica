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

public class ClienteNegarOrcamento extends ServiceProcessos<Orcamento>  {

    public ClienteNegarOrcamento(Orcamento ordemServico) {
        super(ordemServico);
    }

    public void negarOrcamento() {
    
        if (ordemServico.getAvaliacao().getDiagnostico() != EnumDiagnosticoAvaliacao.TemConcerto) {
            throw new RegraBaseException("Orçamento inválido, verifique o Diagnóstico");
        }

        ordemServico.setSituacao(EnumSituacaoOrcamento.Negado);

        List<IServico> servicos = ordemServico.getServicoItens();
        for (IServico servico : servicos) {
            ((ServicoOrcamento) servico).setSituacao(EnumSituacaoOrcamento.Negado);
        }

        ordemServico.setServicoItens(servicos);

        ordemServico.setDataFinalizacao(LocalDateTime.now());
    }

}