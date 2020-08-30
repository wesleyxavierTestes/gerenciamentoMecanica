package com.mecanica.domain.entities.estoque;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "estoque_id")
public class EstoqueEntrada extends AbstractEstoque {

    @Override
    public void configure() {
        if (this.getQuantidade() < 0)
            this.baseConfigure();
    }
}