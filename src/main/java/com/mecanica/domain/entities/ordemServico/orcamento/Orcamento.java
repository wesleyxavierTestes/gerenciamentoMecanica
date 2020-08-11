package com.mecanica.domain.entities.ordemServico.orcamento;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orcamento extends AbstractOrdemServico {

    @Enumerated(EnumType.STRING)
    private EnumSituacaoOrcamento situacao;

    @Column(nullable = false)
    private String descricaoProblema;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Avaliacao avaliacao;
}