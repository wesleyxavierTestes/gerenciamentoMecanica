package com.mecanica.domain.entities.ordemServico.ordemServico;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.mecanica.domain.entities.diasTrabalhados.DiasTrabalhados;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
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


public class OrdemServico extends AbstractOrdemServico {

    @Enumerated(EnumType.STRING)
    private EnumSituacaoOrdemServico situacao;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DiasTrabalhados> diasTrabalhados = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Mecanico mecanico;

    @Size(max = CustomConst.SIZE200, message = ErrorCustomMessage.MAXSIZE + CustomConst.SIZE200)
    private String observacao;

    public int diasRealServico() {
        return diasTrabalhados.size();
    }
}