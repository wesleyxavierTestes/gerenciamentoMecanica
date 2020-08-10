package com.mecanica.domain.entities.categoria;

import javax.persistence.Entity;

import com.mecanica.domain.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria extends BaseEntity {
    
    private String nome;
}