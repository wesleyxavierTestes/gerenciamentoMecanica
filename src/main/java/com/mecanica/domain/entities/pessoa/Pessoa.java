package com.mecanica.domain.entities.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.enuns.EnumTipoPessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa extends BaseEntity {
    @Column(nullable = false, length = 150)
    protected String nome;

    @Column(nullable = true, length = 100)
    protected String email;

    @Column(nullable = true, length = 9)
    protected String rg;

    @Column(nullable = true, length = 11)
    protected String cpf;
  
    @Column(nullable = true, length = 14)
    protected String cnpj;

    @Enumerated(EnumType.STRING)
    protected EnumTipoPessoa tipoPessoa;
   
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected Endereco endereco;
}