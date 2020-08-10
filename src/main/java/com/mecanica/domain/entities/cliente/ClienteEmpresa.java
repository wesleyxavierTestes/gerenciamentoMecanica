package com.mecanica.domain.entities.cliente;

import javax.persistence.Entity;

import com.mecanica.domain.entities.pessoa.PessoaJuridica;

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
public class ClienteEmpresa extends PessoaJuridica implements ICliente {
   
    private String nomeContato;
}