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
public abstract class PessoaFisica extends Pessoa {
    
    @Column(nullable = true, length = 11)
    private String cpf;
}