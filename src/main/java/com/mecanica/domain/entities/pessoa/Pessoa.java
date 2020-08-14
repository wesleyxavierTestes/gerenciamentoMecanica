package com.mecanica.domain.entities.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.enuns.EnumTipoPessoa;
import com.mecanica.application.errors.ErrorCustomMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Pessoa extends BaseEntity {

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false, length = 150)
    protected String nome;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = true, length = 100)
    protected String email;

    @Column(nullable = true, length = 9)
    protected String rg;

    @Column(nullable = true, unique = true, length = 11)
    protected String cpf;
  
    @Column(nullable = true, unique = true, length = 14)
    protected String cnpj;

    @Column(nullable = true, length = 14)
    protected String telefone;

    @Enumerated(EnumType.STRING)
    protected EnumTipoPessoa tipoPessoa;
   
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "endereco_id")
    protected Endereco endereco;
}