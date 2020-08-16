package com.mecanica.domain.entities.ordemServico.ordemServico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.mecanica.application.errors.ErrorCustomMessage;
import com.mecanica.domain.entities.diasTrabalhados.DiasTrabalhados;
import com.mecanica.domain.entities.financeiro.AbstractFinanceiro;
import com.mecanica.domain.entities.financeiro.IFinanceiro;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.ItemOrdemServico;
import com.mecanica.domain.entities.servico.ServicoOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrdemServico;
import com.mecanica.domain.enuns.produto.EnumTipoProduto;
import com.mecanica.utils.CustomConst;

import org.modelmapper.ModelMapper;

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

    @OneToMany(cascade = CascadeType.ALL, targetEntity = AbstractFinanceiro.class)
    protected List<IFinanceiro> itensFinanceiro = new ArrayList<>();

    /**
     * Calcula os dias real informado de trabalho
     * @return
     */
    public int diasRealServico() {
        return diasTrabalhados.size();
    }

    public void configureServicos() {
        List<IServico> list = this.getServicoItens().stream().map(servico -> {
            IServico servicoConvert = (servico.getTipoProduto() == EnumTipoProduto.Servico)
                    ? new ModelMapper().map(servico, ServicoOrdemServico.class)
                    : new ModelMapper().map(servico, ItemOrdemServico.class);
            return servicoConvert;
        }).collect(Collectors.toList());

        this.setServicoItens(list);
    }
}