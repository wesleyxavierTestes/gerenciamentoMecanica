
package com.mecanica.domain.entities.cliente;

import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.mecanica.application.errors.ErrorCustomMessage;
import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.enuns.EnumTipoFormaContato;
import com.mecanica.domain.enuns.EnumTipoPedido;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity


public class ClienteHistoricoRetorno extends BaseEntity {

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @ManyToOne
    private Cliente cliente;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @ManyToOne
    private Funcionario contactor;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Enumerated(EnumType.STRING)
    private EnumTipoPedido tipoPedido;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    private EnumTipoFormaContato tipoFormaContato;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    private String formaContato;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    private String identificacao;

    private String observacao;
}