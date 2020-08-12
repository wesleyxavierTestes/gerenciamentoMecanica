package com.mecanica.domain.entities.servico;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.mecanica.domain.entities.categoria.CategoriaServico;
import com.mecanica.domain.entities.produto.baseentity.AbstractProduto;

import lombok.Setter;

@Setter
@Entity
public class Servico extends AbstractProduto implements IServico {

    @JsonSetter("categoria")
    public void setJsonCategoria(CategoriaServico categoria) {
        this.setCategoria(categoria);
    }  
}