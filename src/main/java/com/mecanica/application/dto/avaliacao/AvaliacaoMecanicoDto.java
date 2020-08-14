package com.mecanica.application.dto.avaliacao;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.mecanica.application.errors.ErrorCustomMessage;
import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.servico.ServicoOrcamento;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AvaliacaoMecanicoDto {

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    private Avaliacao avaliacao;

    private List<ServicoOrcamento> servicos;

    private int dias;
}