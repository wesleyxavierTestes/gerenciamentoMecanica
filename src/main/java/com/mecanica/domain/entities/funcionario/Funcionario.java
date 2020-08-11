
package com.mecanica.domain.entities.funcionario;

import javax.persistence.Entity;

import com.mecanica.domain.entities.pessoa.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario extends Pessoa implements IFuncionario {
    
}