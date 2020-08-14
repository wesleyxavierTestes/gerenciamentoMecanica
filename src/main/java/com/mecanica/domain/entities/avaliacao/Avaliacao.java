package com.mecanica.domain.entities.avaliacao;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;
import com.mecanica.utils.CustomConst;

import com.mecanica.application.errors.ErrorCustomMessage;

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

    private LocalDateTime dataInicial = LocalDateTime.now();
    private LocalDateTime dataFinalizacao = LocalDateTime.now();

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Enumerated(EnumType.STRING)
    private EnumDiagnosticoAvaliacao diagnostico;

    @ManyToOne
    private Mecanico mecanico;

    @Size(max = CustomConst.SIZE200, message = ErrorCustomMessage.MAXSIZE + CustomConst.SIZE200)
    private String observacao;
}