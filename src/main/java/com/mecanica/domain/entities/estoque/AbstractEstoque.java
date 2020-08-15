package com.mecanica.domain.entities.estoque;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.mecanica.application.errors.ErrorCustomMessage;
import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.produto.Produto;

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
    
    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @ManyToOne
    private Produto produto;

    protected void baseConfigure() {
        this.setQuantidade(this.getQuantidade() * (-1));
    }
}