package com.mecanica.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.mecanica.application.converters.LocalDateConverter;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    protected UUID id = UUID.randomUUID();

    @Column(nullable = false)
    @Convert(converter = LocalDateConverter.class)
    protected LocalDateTime dataCadastro = LocalDateTime.now();
}