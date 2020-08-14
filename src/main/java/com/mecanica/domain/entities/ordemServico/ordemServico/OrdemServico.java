package com.mecanica.domain.entities.ordemServico.ordemServico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.mecanica.application.errors.ErrorCustomMessage;
import com.mecanica.domain.entities.diasTrabalhados.DiasTrabalhados;
import com.mecanica.domain.entities.financeiro.AbstractFinanceiro;
import com.mecanica.domain.entities.financeiro.IFinanceiro;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.utils.CustomConst;

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

    @OneToMany(cascade = CascadeType.ALL)
    private Set<DiasTrabalhados> diasTrabalhados = new HashSet<>();

    @OneToMany
    private List<Mecanico> mecanicos = new ArrayList<>();

    @Size(max = CustomConst.SIZE200, message = ErrorCustomMessage.MAXSIZE + CustomConst.SIZE200)
    private String observacao;

    @OneToMany(
        fetch = FetchType.EAGER, targetEntity = AbstractFinanceiro.class)
    protected List<IFinanceiro> itensFinanceiro = new ArrayList<>();

    public int diasRealServico() {
        return diasTrabalhados.size();
    }
}