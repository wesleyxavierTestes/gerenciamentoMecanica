package com.mecanica.domain.entities.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.mecanica.application.errors.ErrorCustomMessage;
import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.cliente.Cliente;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Veiculo extends BaseEntity {

    @ApiModelProperty(name = "Cliente")
    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @ManyToOne
    private Cliente cliente;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false)
    private String nome;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false)
    private String cor;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false)
    private String placa;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false, unique = true)
    private String renavam;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false)
    private String modelo;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false)
    private String marca;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false)
    private String ano;
}