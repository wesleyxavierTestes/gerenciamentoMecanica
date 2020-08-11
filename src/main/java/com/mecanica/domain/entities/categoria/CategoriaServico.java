package com.mecanica.domain.entities.categoria;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("servico")
public class CategoriaServico extends AbstractCategoria {
    
}