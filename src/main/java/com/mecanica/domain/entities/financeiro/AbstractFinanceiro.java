package com.mecanica.domain.entities.financeiro;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "financeiro")

@Table(name = "financeiro")
@DiscriminatorColumn(name = "financeiro_tipo")

public abstract class AbstractFinanceiro extends BaseEntity implements IFinanceiro {

    @ManyToOne
    protected OrdemServico ordemServico;

    @Column(nullable = false)
    protected BigDecimal valor;

    protected void baseConfigure() {
        this.setValor(this.getValor().multiply(BigDecimal.valueOf(-1l)));
    }
}