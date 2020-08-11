package com.mecanica.domain.entities.categoria;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.mecanica.domain.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "categoria")
@Table(name = "categoria")
@DiscriminatorColumn(name = "categoria_tipo")
public class AbstractCategoria extends BaseEntity {
    
    protected String nome;
}