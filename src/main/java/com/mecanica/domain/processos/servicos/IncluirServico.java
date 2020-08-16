package com.mecanica.domain.processos.servicos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.entities.servico.ServicoOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

import org.modelmapper.ModelMapper;

public class IncluirServico extends ServiceProcessos<OrdemServico> {

    public IncluirServico(OrdemServico ordemServico) {
        super(ordemServico);
    }

    public void incluirServico(Servico servico) {

        if (ordemServico.getSituacao() == EnumSituacaoOrdemServico.Cancelado
                || ordemServico.getSituacao() == EnumSituacaoOrdemServico.Finalizado) {
            throw new RegraBaseException("Verifique o Diagnóstico");
        }

        ServicoOrdemServico servicoOrdemServico = new ModelMapper().map(servico, ServicoOrdemServico.class);
        
        servicoOrdemServico.setId(UUID.randomUUID());
        servicoOrdemServico.setDataCadastro(LocalDateTime.now());
        servicoOrdemServico.setSituacao(ordemServico.getSituacao());

        List<IServico> list = ordemServico.getServicoItens();
        
        list.add(servicoOrdemServico);

        ordemServico.setServicoItens(list);
        
        ordemServico.calcularValorTotal();
    }

}