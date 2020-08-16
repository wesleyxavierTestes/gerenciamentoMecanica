package com.mecanica.domain.entities.ordemServico;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.utils.CustomConst;

import org.modelmapper.ModelMapper;

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

    private int diasEstimadoServico;
    private boolean pago;

    protected LocalDate dataPrevisaoInicio;
    protected LocalDate dataPrevisaoFinalizacao;

    protected LocalDateTime dataInicial;
    protected LocalDateTime dataFinalizacao;

    // @JsonGetter("dataPrevisaoInicio")
    // public String getDataPrevisaoInicio() {
    //     String data = Objects.nonNull(this.dataPrevisaoInicio) ? this.dataPrevisaoInicio.toString() : null;
    //     return data;
    // }

    // @JsonSetter("dataPrevisaoInicio")
    // public void setDataPrevisaoInicio(String dataPrevisaoInicio) {
    //     LocalDate data = Objects.nonNull(this.dataPrevisaoInicio) ? LocalDate.parse(dataPrevisaoInicio) : null;
    //     this.dataPrevisaoInicio = data;
    // }

    // @JsonGetter("dataPrevisaoFinalizacao")
    // public String getDataPrevisaoFinalizacao() {
    //     String data = Objects.nonNull(this.dataPrevisaoFinalizacao) ? this.dataPrevisaoFinalizacao.toString() : null;
    //     return data;
    // }

    // @JsonSetter("dataPrevisaoFinalizacao")
    // public void setDataPrevisaoFinalizacao(String dataPrevisaoFinalizacao) {
    //     LocalDate data = Objects.nonNull(this.dataPrevisaoFinalizacao) ? LocalDate.parse(dataPrevisaoFinalizacao) : null;
    //     this.dataPrevisaoFinalizacao = data;
    // }

    // @JsonGetter("dataInicial")
    // public String getDataInicial() {
    //     String data = Objects.nonNull(this.dataInicial) ? this.dataInicial.toString() : null;
    //     return data;
    // }

    // @JsonSetter("dataInicial")
    // public void setDataInicial(String dataInicial) {
    //     LocalDateTime data = Objects.nonNull(this.dataInicial) ? LocalDateTime.parse(dataInicial) : null;
    //     this.dataInicial = data;
    // }

    // @JsonGetter("dataFinalizacao")
    // public String getDataFinalizacao() {
    //     String data = Objects.nonNull(this.dataFinalizacao) ? this.dataFinalizacao.toString() : null;
    //     return data;
    // }

    // @JsonSetter("dataFinalizacao")
    // public void setDataFinalizacao(String dataFinalizacao) {
    //     LocalDateTime data = Objects.nonNull(this.dataFinalizacao) ?  LocalDateTime.parse(dataFinalizacao) : null;
    //     this.dataFinalizacao = data;
    // }

    // @JsonGetter("dataCancelamento")
    // public String getDataCancelamento() {
    //     String data = Objects.nonNull(this.dataCancelamento) ? this.dataCancelamento.toString() : null;
    //     return data;
    // }

    // @JsonSetter("dataCancelamento")
    // public void setDataCancelamento(String dataCancelamento) {
    //     this.dataCancelamento =  Objects.nonNull(this.dataCancelamento) ? LocalDate.parse(dataCancelamento) : null;
    // }

    public LocalDate dataCancelamento;

    @Column(nullable = false)
    protected BigDecimal valor = BigDecimal.ZERO;

    @Column(nullable = false)
    protected BigDecimal valorDesconto = BigDecimal.ZERO;

    @Column(nullable = false)
    protected BigDecimal valorTotal = BigDecimal.ZERO;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Servico.class)
    protected List<IServico> servicoItens = new ArrayList<>();

    @JsonGetter("atendente")
    public IFuncionario getJsonAtendente() {
        return this.getAtendente();
    }

    @JsonSetter("atendente")
    public void getJsonAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    /**
     * Calcula os dias corridos de trabalho
     * 
     * @return
     */
    public long diasCorridoServico() {
        if (!Objects.nonNull(dataInicial))
            return 0;
        if (!Objects.nonNull(dataFinalizacao))
            dataFinalizacao = LocalDateTime.now();

        return ChronoUnit.DAYS.between(dataInicial, dataFinalizacao);
    }

    /**
     * Calcula os dias corridos de atraso
     * 
     * @return
     */
    public long diasCorridoAtraso() {
        if (!Objects.nonNull(dataFinalizacao))
            return 0;
        if (!Objects.nonNull(dataPrevisaoFinalizacao))
            dataPrevisaoFinalizacao = LocalDate.now();

        return ChronoUnit.DAYS.between(dataPrevisaoFinalizacao, dataFinalizacao);
    }

    private BigDecimal getSomaValor() {
        return this.servicoItens.stream().map(IServico::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getSomaValorTotal() {
        if (!Objects.nonNull(valorDesconto)) {
            valorDesconto = BigDecimal.ZERO;
        }
        BigDecimal valor = this.getSomaValor();
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

    public <T extends AbstractOrdemServico> T getClone(Class<T> type) {
        return new ModelMapper().map(this, type);
    }

    public void calcularValorTotal() {
        this.setValor(this.getSomaValor());
        this.setValorTotal(this.getSomaValorTotal());
    }

    public abstract void configureServicos();
}