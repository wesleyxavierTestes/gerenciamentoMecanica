package com.mecanica.domain.entities.itemServico;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.categoria.Categoria;
import com.mecanica.domain.entities.servico.AbstractServico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractItemServico extends BaseEntity {
    
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private AbstractServico servico;
}