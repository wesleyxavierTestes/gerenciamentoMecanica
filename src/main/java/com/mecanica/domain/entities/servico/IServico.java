package com.mecanica.domain.entities.servico;

import java.math.BigDecimal;

import com.mecanica.domain.entities.produto.baseentity.IProduto;




public interface IServico extends IProduto {

    BigDecimal getValor();

    <T extends IServico> T getClone(Class<T> type);
}
