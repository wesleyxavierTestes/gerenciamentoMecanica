package com.mecanica.domain.entities.pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.mecanica.domain.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco extends BaseEntity {
    @Column(nullable = true, length = 8)
    private String cep;

    @Column(nullable = true, length = 50)
    private String numero;
    
    @Column(nullable = false, length = 100)
    private String logradouro;

    @Column(nullable = true, length = 100)
    private String complemento;

    @Column(nullable = false, length = 200)
    private String bairro;

    @Column(nullable = false, length = 50)
    private String localidade;

    @Column(nullable = false, length = 2)
    private String uf;

    @Column(nullable = true, length = 30)
    private String unidade;

    @Column(nullable = true, length = 30)
    private String ibge;

    @Column(nullable = true, length = 14)
    private String gia;
}