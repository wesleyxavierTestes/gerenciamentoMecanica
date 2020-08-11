package com.mecanica.domain.entities.avaliacao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Avaliacao extends BaseEntity {
    
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinalizacao;   

    @Enumerated(EnumType.STRING)
    private EnumDiagnosticoAvaliacao diagnostico;
    
    @ManyToOne
    private Mecanico ordemServico;

    private String observacao;
}