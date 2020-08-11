package com.mecanica.domain.entities.estoque;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.mecanica.domain.entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "estoque")
@Table(name = "estoque")
@DiscriminatorColumn(name = "estoque_tipo")
public abstract class AbstractEstoque extends BaseEntity implements IEstoque {

    @Column(nullable = false)
    protected int quantidade;

    protected void baseConfigure() {
        this.setQuantidade(this.getQuantidade() * (-1));
    }
}