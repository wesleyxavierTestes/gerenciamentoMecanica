package com.mecanica.domain.entities.produto.baseentity;

import java.util.UUID;

import com.mecanica.domain.entities.IBaseEntity;
import com.mecanica.domain.entities.categoria.ICategoria;

public interface IProduto extends IBaseEntity {
    
    void setCodigo(UUID codigo);

    UUID getCodigo();

    <T extends ICategoria> void setICategoria(T categoria);
}