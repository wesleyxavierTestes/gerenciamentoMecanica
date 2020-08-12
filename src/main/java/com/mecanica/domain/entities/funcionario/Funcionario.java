
package com.mecanica.domain.entities.funcionario;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.mecanica.domain.entities.pessoa.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name = "setor")
@DiscriminatorValue("Geral")
public class Funcionario extends Pessoa implements IFuncionario {
    
}