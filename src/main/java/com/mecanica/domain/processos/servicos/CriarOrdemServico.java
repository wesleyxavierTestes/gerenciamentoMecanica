package com.mecanica.domain.processos.servicos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.ServicoOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class CriarOrdemServico extends ServiceProcessos<Orcamento> {

    public CriarOrdemServico(Orcamento ordemServico) {
        super(ordemServico);
    }

    public OrdemServico configurarNovaOrdemServico() {

        AbstractOrdemServico servicoConverter = (AbstractOrdemServico) ordemServico;

        List<IServico> lista = servicoConverter.getServicoItens()
        .stream().map(servico -> mapConverterServico(servico)).collect(Collectors.toList());

        servicoConverter.setServicoItens(lista);

        OrdemServico novaOrdemServico = servicoConverter.getClone(OrdemServico.class);

        novaOrdemServico.setId(UUID.randomUUID());
        novaOrdemServico.setDataCadastro(LocalDateTime.now());

        novaOrdemServico.setSituacao(EnumSituacaoOrdemServico.Aguardando);
        

        return novaOrdemServico;
    }

    private ServicoOrdemServico mapConverterServico(IServico servico) {
        ServicoOrdemServico servicoConverter = servico.getClone(ServicoOrdemServico.class);
        
        servicoConverter.setId(UUID.randomUUID());
        servicoConverter.setDataCadastro(LocalDateTime.now());

        return servicoConverter;
    }
}