package com.mecanica.domain.entities.ordemServico.ordemServico;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mecanica.domain.entities.diasTrabalhados.DiasTrabalhados;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrdemServico extends AbstractOrdemServico {
    
    @Enumerated(EnumType.STRING)
    private EnumSituacaoOrdemServico situacao;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DiasTrabalhados> diasTrabalhados;

    @ManyToOne(fetch = FetchType.LAZY)
    private Mecanico mecanico;

    private String observacao;
}