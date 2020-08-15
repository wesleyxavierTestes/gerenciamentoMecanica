package com.mecanica.domain.entities.produto.baseentity;

import java.util.UUID;

import com.mecanica.domain.entities.IBaseEntity;
import com.mecanica.domain.entities.categoria.ICategoria;
import com.mecanica.domain.enuns.produto.EnumTipoProduto;

public interface IProduto extends IBaseEntity {
    
    void setCodigo(UUID codigo);

    UUID getCodigo();

    <T extends IProduto> T getClone(Class<T> type);

    EnumTipoProduto getTipoProduto();
    AbstractProduto setTipoProduto(EnumTipoProduto tipoProduto);

    <T extends ICategoria> void setICategoria(T categoria);
}