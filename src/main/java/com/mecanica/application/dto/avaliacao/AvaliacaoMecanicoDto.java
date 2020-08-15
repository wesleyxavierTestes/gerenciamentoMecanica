package com.mecanica.application.dto.avaliacao;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.mecanica.application.errors.ErrorCustomMessage;
import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.produto.Produto;
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
    private String mecanicoCpf;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    private String identificacao;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    private Avaliacao avaliacao;

    private List<ItemServicoDto> servicos;
    private List<ItemServicoDto> itensServico;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    private Integer dias;
    
    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    private LocalDate dataPrevisaoInicio;
}