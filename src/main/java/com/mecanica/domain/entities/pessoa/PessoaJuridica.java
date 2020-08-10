package com.mecanica.domain.entities.pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class PessoaJuridica extends Pessoa {
    
    @Column(nullable = true, length = 14)
    private String cnjp;
}