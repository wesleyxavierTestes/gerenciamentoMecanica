package com.mecanica.domain.entities.categoria;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.mecanica.domain.entities.BaseEntity;



import com.mecanica.application.errors.ErrorCustomMessage;

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

public class Categoria extends BaseEntity implements ICategoria {

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(unique = true, nullable = false)
    protected String nome;
}