package com.mecanica.domain.entities.categoria;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "CategoriaProduto")
@DiscriminatorValue("produto")
public class CategoriaProduto extends Categoria {

}