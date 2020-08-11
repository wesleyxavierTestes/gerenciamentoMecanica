package com.mecanica.domain.entities.produto.baseentity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.categoria.CategoriaProduto;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.enuns.produto.EnumTipoProduto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractProduto extends BaseEntity {

    @Column(nullable = false)
    private UUID codigo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private CategoriaProduto categoria;

    @ManyToOne
    private OrdemServico pedido;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumTipoProduto tipoProduto;

    public AbstractProduto setTipoProduto(EnumTipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
        return this;
    }
}