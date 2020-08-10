
package com.mecanica.domain.entities.funcionario;

import javax.persistence.Entity;

import com.mecanica.domain.entities.pessoa.PessoaFisica;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario extends PessoaFisica implements IFuncionario {
    
}