package com.mecanica.application.dto.avaliacao;

import javax.validation.constraints.NotNull;

import com.mecanica.application.errors.ErrorCustomMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemServicoDto {

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    private String Id;
}