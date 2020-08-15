package com.mecanica.domain.entities.estoque;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("S")
public class EstoqueSaida extends AbstractEstoque {

    @Override
    public void configure() {
        if (this.getQuantidade() > 0)
            this.baseConfigure();
    }
}