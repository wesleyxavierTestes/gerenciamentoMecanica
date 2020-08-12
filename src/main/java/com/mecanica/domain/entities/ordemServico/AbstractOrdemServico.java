package com.mecanica.domain.entities.ordemServico;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.entities.veiculo.Veiculo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractOrdemServico extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    protected Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Funcionario.class)
    protected IFuncionario atendente;

    protected LocalDateTime dataInicial = LocalDateTime.now();;
    protected LocalDateTime dataFinalizacao;

    @Column(nullable = false)
    protected BigDecimal valorDesconto;

    @Column(nullable = false)
    protected BigDecimal valorTotal;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Servico.class)
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
}