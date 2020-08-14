package com.mecanica.domain.entities.ordemServico;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.pessoa.Pessoa;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.utils.CustomConst;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.mecanica.application.errors.ErrorCustomMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractOrdemServico extends BaseEntity {

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Size(max = CustomConst.SIZE50, message = ErrorCustomMessage.MAXSIZE + CustomConst.SIZE50)
    @Column(nullable = false)
    private String identificacao;

    @ManyToOne
    protected Cliente cliente;

    @ManyToOne
    protected Veiculo veiculo;

    @JsonIgnore
    @ManyToOne(targetEntity = Funcionario.class)
    protected IFuncionario atendente;

    @JsonGetter("atendente")
    public IFuncionario getJsonAtendente() {
        return this.getAtendente();
    }

    @JsonSetter("atendente")
    public void getJsonAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    private int diasEstimadoServico;

    protected LocalDateTime dataInicial = LocalDateTime.now();
    protected LocalDateTime dataFinalizacao;

    @Column(nullable = false)
    protected BigDecimal valorDesconto = BigDecimal.ZERO;

    @Column(nullable = false)
    protected BigDecimal valorTotal = BigDecimal.ZERO;

    @OneToMany(cascade = CascadeType.DETACH, targetEntity = Servico.class)
    @JoinTable(name = "ServicoItens")
    protected List<IServico> servicoItens = new ArrayList<>();

    public BigDecimal getValor() {
        return this.servicoItens.stream().map(IServico::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getValorTotal() {
        if (!Objects.nonNull(valorDesconto)) {
            valorDesconto = BigDecimal.ZERO;
        }
        BigDecimal valor = this.getValor();
        if (!Objects.nonNull(valor)) {
            valor = BigDecimal.ZERO;
        }
        return valor.subtract(this.valorDesconto);
    }

    public void setServicoItem(IServico servico) {
        if (!Objects.nonNull(servicoItens))
            servicoItens = new ArrayList<>();

        this.servicoItens.add(servico);
    }
}