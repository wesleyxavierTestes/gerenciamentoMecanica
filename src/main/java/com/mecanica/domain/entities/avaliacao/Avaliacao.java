package com.mecanica.domain.entities.avaliacao;

import java.time.LocalDateTime;
import java.util.Objects;

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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
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

    @JsonIgnore
    private LocalDateTime dataInicial = LocalDateTime.now();

    @JsonIgnore
    private LocalDateTime dataFinalizacao = LocalDateTime.now();

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)

    // @JsonGetter("dataInicial")
    // public String getDataInicial() {
    //     @NotNull(message = "Item Obrigatório")
    //     String data = Objects.nonNull(this.dataInicial) ? this.dataInicial.toString() : null;
    //     return data;
    // }

    // @JsonSetter("dataInicial")
    // public void setDataInicial(String dataInicial) {
    //     LocalDateTime data = Objects.nonNull(this.dataInicial) ? LocalDateTime.parse(dataInicial) : null;
    //     this.dataInicial = data;
    // }

    // @JsonGetter("dataFinalizacao")
    // public LocalDateTime getDataFinalizacao() {
    //     LocalDateTime data = Objects.nonNull(this.dataFinalizacao) ?  this.dataFinalizacao : null;
    //     return data;
    // }

    // @JsonSetter("dataFinalizacao")
    // public void setDataFinalizacao(String dataFinalizacao) {
    //     LocalDateTime data = Objects.nonNull(this.dataFinalizacao) ?  LocalDateTime.parse(dataFinalizacao) : null;
    //     this.dataFinalizacao = data;
    // }

    @Enumerated(EnumType.STRING)
    private EnumDiagnosticoAvaliacao diagnostico;

    @ManyToOne
    private Mecanico mecanico;

    @Size(max = CustomConst.SIZE200, message = ErrorCustomMessage.MAXSIZE + CustomConst.SIZE200)
    private String observacao;
}