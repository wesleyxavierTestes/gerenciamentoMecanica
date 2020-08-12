
package com.mecanica.domain.entities.mecanico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.mecanica.domain.entities.funcionario.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("Mecânico")
public class Mecanico extends Funcionario {
    
}