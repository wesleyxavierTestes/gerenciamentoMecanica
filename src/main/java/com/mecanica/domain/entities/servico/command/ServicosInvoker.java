package com.mecanica.domain.entities.servico.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;

public class ServicosInvoker {
    public static List<ICommand> comandos = new ArrayList<>();

    public static void invoke(OrdemServico ordemServico) {
        for (ICommand iCommand : comandos) {
            boolean executadoComSucesso = iCommand.Acao(ordemServico);
            if (executadoComSucesso) {
                comandos.remove(iCommand);
            }
        }
    }
}