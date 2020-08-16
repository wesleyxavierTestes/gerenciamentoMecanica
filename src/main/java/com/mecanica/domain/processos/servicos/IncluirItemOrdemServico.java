package com.mecanica.domain.processos.servicos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.ItemOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

import org.modelmapper.ModelMapper;

public class IncluirItemOrdemServico extends ServiceProcessos<OrdemServico> {

    public IncluirItemOrdemServico(OrdemServico ordemServico) {
        super(ordemServico);
    }

    public void incluirItemOrdemServico(Produto produto) {
        if (ordemServico.getSituacao() == EnumSituacaoOrdemServico.Cancelado
                || ordemServico.getSituacao() == EnumSituacaoOrdemServico.Finalizado) {
            throw new RegraBaseException("Verifique o Diagnóstico");
        }

        ItemOrdemServico itemServico = new ModelMapper().map(produto, ItemOrdemServico.class);
                
        itemServico.setId(UUID.randomUUID());
        itemServico.setDataCadastro(LocalDateTime.now());

        List<IServico> list = ordemServico.getServicoItens();
        
        list.add(itemServico);

        ordemServico.setServicoItens(list);

        ordemServico.calcularValorTotal();
    }

}