package com.mecanica.domain.entities.financeiro;

import java.math.BigDecimal;

public interface IFinanceiro {
    
    void configure();

    void setValor(BigDecimal valor);
    BigDecimal getValor();
}