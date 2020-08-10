package com.mecanica.domain.entities.servico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.cliente.ICliente;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.pessoa.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractServico extends BaseEntity {
    
    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pessoa.class)
    private ICliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Funcionario.class)
    private IFuncionario atendente;

    private LocalDateTime dataInicial;
    private LocalDateTime dataFinalizacao;    
}