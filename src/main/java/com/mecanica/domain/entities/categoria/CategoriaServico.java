package com.mecanica.domain.entities.categoria;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "CategoriaServico")
@DiscriminatorValue("servico")
public class CategoriaServico extends Categoria {
}