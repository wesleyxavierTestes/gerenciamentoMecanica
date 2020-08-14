package com.mecanica.domain.entities.produto.baseentity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.categoria.Categoria;
import com.mecanica.domain.entities.categoria.ICategoria;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.enuns.produto.EnumTipoProduto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractProduto extends BaseEntity implements IProduto {

    @Column(nullable = false)
    protected UUID codigo;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Categoria.class)
    protected ICategoria categoria;

    @Column(nullable = false)
    protected String nome;

    @Column(nullable = false)
    protected BigDecimal valor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected EnumTipoProduto tipoProduto;

    @OneToMany(
        fetch = FetchType.EAGER, cascade = CascadeType.ALL , targetEntity = AbstractProduto.class)
        @JoinTable(name = "ProdutoItens")
    protected List<IProduto> itens = new ArrayList<>();

    public AbstractProduto setTipoProduto(EnumTipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
        return this;
    }

    @JsonGetter("categoria")
    public ICategoria getJsonCategoria() {
        return this.getCategoria();
    }
}