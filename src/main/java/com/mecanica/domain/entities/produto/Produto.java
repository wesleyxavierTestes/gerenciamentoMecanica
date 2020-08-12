package com.mecanica.domain.entities.produto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.mecanica.domain.entities.categoria.CategoriaProduto;
import com.mecanica.domain.entities.categoria.ICategoria;
import com.mecanica.domain.entities.estoque.AbstractEstoque;
import com.mecanica.domain.entities.estoque.IEstoque;
import com.mecanica.domain.entities.produto.baseentity.AbstractProduto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto extends AbstractProduto {

    /**
     * Estoques FindAllByProdutoId
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, targetEntity = AbstractEstoque.class)
    private List<IEstoque> estoque;

    @Transient
    private double estoqueAtual;

    @Column(nullable = false)
    private BigDecimal custo;

    @JsonSetter("categoria")
    public void setJsonCategoria(CategoriaProduto categoria) {
        this.setICategoria((ICategoria)categoria);
    }

    @Override
    public <T extends ICategoria> void setICategoria(T categoria) {
        this.setCategoria((ICategoria)categoria);
    }
}