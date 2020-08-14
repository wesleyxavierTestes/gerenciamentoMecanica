package com.mecanica.domain.processos.servicos;

import java.util.List;
import java.util.stream.Collectors;

import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.ServicoOrdemServico;
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

        OrdemServico novaOrdemServico = (OrdemServico) servicoConverter;

        return novaOrdemServico;
    }

    private ServicoOrdemServico mapConverterServico(IServico servico) {
        IServico servicoConverter = servico;
        return (ServicoOrdemServico)servicoConverter;
    }
}