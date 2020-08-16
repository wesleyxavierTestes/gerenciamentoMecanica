package com.mecanica.domain.processos.servicos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.ItemOrcamento;
import com.mecanica.domain.entities.servico.ItemOrdemServico;
import com.mecanica.domain.entities.servico.ServicoOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class CriarOrdemServico extends ServiceProcessos<Orcamento> {

    public CriarOrdemServico(Orcamento ordemServico) {
        super(ordemServico);
    }

    public OrdemServico configurarNovaOrdemServico() {

        AbstractOrdemServico servicoConverter = (AbstractOrdemServico) ordemServico;

        List<IServico> lista = new ArrayList<>(servicoConverter.getServicoItens())
        .stream().map(servico -> mapConverterServico(servico)).collect(Collectors.toList());

        OrdemServico novaOrdemServico = servicoConverter.getClone(OrdemServico.class);

        novaOrdemServico.setServicoItens(lista);

        novaOrdemServico.setId(UUID.randomUUID());
        novaOrdemServico.setDataCadastro(LocalDateTime.now());

        novaOrdemServico.setSituacao(EnumSituacaoOrdemServico.Aguardando);
        
        return novaOrdemServico;
    }

    private IServico mapConverterServico(IServico servico) {
        IServico servicoConverter = (servico instanceof ItemOrcamento)
        ? servico.getClone(ItemOrdemServico.class)
        : servico.getClone(ServicoOrdemServico.class);
        
        servicoConverter.setId(UUID.randomUUID());
        servicoConverter.setDataCadastro(LocalDateTime.now());

        return servicoConverter;
    }
}