package com.mecanica.domain.entities.servico.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;

final class ServicosInvoker {

    private ServicosInvoker() {}

    private static List<IServicoCommand> comandos = new ArrayList<>();
    
    public static void setComando(IServicoCommand command) {
        
        comandos.add(command);
    }

    public static void exists(IServicoCommand command) {

        comandos.add(command);
    }

    public static void invoke(OrdemServico ordemServico) {
        
        List<IServicoCommand> comandosFiltrado = comandos.stream()
        .filter(c -> Objects.nonNull(c.getordemServico())
            && c.getordemServico().getId().equals(ordemServico.getId()))
            .collect(Collectors.toList());

        for (IServicoCommand iCommand : comandosFiltrado) {
            boolean executadoComSucesso = iCommand.Acao(ordemServico);
            if (executadoComSucesso) {
                comandos.remove(iCommand);
            }
        }
    }
}