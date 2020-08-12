package com.mecanica.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ErrorCustomMessage {

    private String propriedade;
    private String mensagem;

    private ErrorCustomMessage() {}

    public static final String OBRIGATORIO = "Item Obrigatório";
    
}