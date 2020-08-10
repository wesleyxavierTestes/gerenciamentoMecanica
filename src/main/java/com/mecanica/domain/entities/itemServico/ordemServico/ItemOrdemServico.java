package com.mecanica.domain.entities.itemServico.ordemServico;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.mecanica.domain.entities.itemServico.AbstractItemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemOrdemServico extends AbstractItemServico {
    
    /** 
     * Refere-se a data qual este item de serviço foi finalizado
     */
    private LocalDateTime dataFinalizacao;
    /**
     * Observação referente ações pertinente de execução
     */
    private String observacao;
}