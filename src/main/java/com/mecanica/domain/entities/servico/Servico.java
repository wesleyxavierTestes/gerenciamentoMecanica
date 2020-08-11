package com.mecanica.domain.entities.servico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.categoria.CategoriaProduto;
import com.mecanica.domain.entities.categoria.CategoriaServico;
import com.mecanica.domain.entities.produto.Produto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
public class Servico extends BaseEntity {
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private CategoriaServico categoria;

    @Column(nullable = false)
    private String codigoPrestacaoServico;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Produto> ItemComposicao = new ArrayList<>();
}