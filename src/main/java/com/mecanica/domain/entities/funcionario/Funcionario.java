
package com.mecanica.domain.entities.funcionario;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.mecanica.domain.entities.pessoa.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn( name = "setor")
@DiscriminatorValue("Geral")
@Entity(name = "funcionario")
@Table(name = "funcionario")
public class Funcionario extends Pessoa implements IFuncionario {
    
}