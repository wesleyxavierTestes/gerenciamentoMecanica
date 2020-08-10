package com.mecanica.domain.entities.servico.ordemServico;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mecanica.domain.entities.diasTrabalhados.DiasTrabalhados;
import com.mecanica.domain.entities.itemServico.ordemServico.ItemOrdemServico;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.servico.AbstractServico;
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
public class OrdemServico extends AbstractServico {
    
    @Enumerated(EnumType.STRING)
    private EnumSituacaoOrdemServico situacao;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DiasTrabalhados> diasTrabalhados;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ItemOrdemServico> itensOrdemServico;

    @ManyToOne(fetch = FetchType.LAZY)
    private Mecanico mecanico;
}