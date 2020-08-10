package com.mecanica.domain.entities.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends BaseEntity {
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = true, length = 100)
    private String email;

    @Column(nullable = true, length = 9)
    private String rg;

    @Column(nullable = true, length = 11)
    private String cpf;
    
    @Column(nullable = true, length = 14)
    private String cnjp;

    @Enumerated(EnumType.STRING)
    private EnumTipoPessoa tipoPessoa;
   
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Endereco endereco;
}