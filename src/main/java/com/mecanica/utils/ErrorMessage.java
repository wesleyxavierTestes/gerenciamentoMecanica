package com.mecanica.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ErrorMessage {

    private String propriedade;
    private String mensagem;

    private ErrorMessage() {}

    public static final String OBRIGATORIO = "Item Obrigatório";
    
}