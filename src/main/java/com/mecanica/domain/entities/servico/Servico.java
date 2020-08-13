package com.mecanica.domain.entities.servico;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.mecanica.domain.entities.categoria.CategoriaServico;
import com.mecanica.domain.entities.categoria.ICategoria;
import com.mecanica.domain.entities.produto.baseentity.AbstractProduto;
import com.mecanica.utils.CustomConst;
import com.mecanica.application.errors.ErrorCustomMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Servico extends AbstractProduto implements IServico {

    @JsonSetter("categoria")
    public void setJsonCategoria(CategoriaServico categoria) {
        this.setCategoria((ICategoria)categoria);
    }

    @Size(max = CustomConst.SIZE200, message = ErrorCustomMessage.MAXSIZE + CustomConst.SIZE200)
    private String observacao;

    @Override
    public <T extends ICategoria> void setICategoria(T categoria) {
        this.setCategoria((ICategoria)categoria);
    }
    
}