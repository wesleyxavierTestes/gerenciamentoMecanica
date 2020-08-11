package com.mecanica.domain.entities.servico;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.categoria.Categoria;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
public class Servico extends BaseEntity {
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private Categoria categoria;

    @Column(nullable = false)
    private String codigoPrestacaoServico;

    @Override
    public Servico clone() throws CloneNotSupportedException {
        return (Servico) super.clone();
    }
}