package com.mecanica.domain.entities.veiculo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.cliente.ICliente;
import com.mecanica.domain.entities.pessoa.Pessoa;

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
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pessoa.class)
    private ICliente cliente;
    
    @Column(nullable = false)
    private String placa;

    @Column(nullable = false)
    private String renavam;
    
    @Column(nullable = false)
    private String modelo;
    
    @Column(nullable = false)
    private String marca;
    
    @Column(nullable = false)
	private String ano;
}