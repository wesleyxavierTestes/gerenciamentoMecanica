
package com.mecanica.domain.entities.cliente;

import javax.persistence.Entity;

import com.mecanica.domain.entities.pessoa.PessoaFisica;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
public class ClienteComum extends PessoaFisica implements ICliente {
    
}