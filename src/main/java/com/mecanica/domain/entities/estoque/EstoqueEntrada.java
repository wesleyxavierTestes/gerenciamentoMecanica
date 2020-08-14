package com.mecanica.domain.entities.estoque;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@DiscriminatorValue("E")

public class EstoqueEntrada extends AbstractEstoque {

    @Override
    public void configure() {
        if (this.getQuantidade() < 0)
            this.baseConfigure();
    }
}