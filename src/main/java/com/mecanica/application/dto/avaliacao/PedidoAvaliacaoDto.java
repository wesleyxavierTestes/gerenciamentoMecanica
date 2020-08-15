package com.mecanica.application.dto.avaliacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoAvaliacaoDto {

    private String funcionarioCpf;
    private String clienteId;
    private String veiculoRenavam;

    
    private String descricaoProblema;
}