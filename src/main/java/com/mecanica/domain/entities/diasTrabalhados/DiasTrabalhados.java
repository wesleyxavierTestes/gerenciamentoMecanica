package com.mecanica.domain.entities.diasTrabalhados;

import javax.persistence.Entity;

import javax.persistence.ManyToOne;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DiasTrabalhados extends BaseEntity {

    @ManyToOne
    private OrdemServico ordemServico;
}