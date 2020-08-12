package com.mecanica.domain.entities.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.utils.ErrorCustomMessage;

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
    
    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @ManyToOne
    private Cliente cliente;
    
    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false)
    private String placa;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false)
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