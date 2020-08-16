package com.mecanica.domain.entities.financeiro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("S")
public class FinanceiroSaida extends AbstractFinanceiro {

    @Override
    public void configure() {
        if (this.getValor().doubleValue() > 0)
            this.baseConfigure();
    }
}