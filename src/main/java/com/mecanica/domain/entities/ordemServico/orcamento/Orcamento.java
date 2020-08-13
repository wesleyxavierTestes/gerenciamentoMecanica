package com.mecanica.domain.entities.ordemServico.orcamento;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.utils.CustomConst;
import com.mecanica.application.errors.ErrorCustomMessage;

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
public class Orcamento extends AbstractOrdemServico {

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumSituacaoOrcamento situacao;

    @NotNull(message = ErrorCustomMessage.OBRIGATORIO)
    @Size(max = CustomConst.SIZE200, message = ErrorCustomMessage.MAXSIZE + CustomConst.SIZE200)
    @Column(nullable = false)
    private String descricaoProblema;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Avaliacao avaliacao;

    /**
     * Gera Identificação com base no nome cliente e renavam do carro
     * @return
     */
    public String getCustoIdentificacao() {
        try {
            final String identificacao = String.format("%s- %s", this.cliente.getNome(), this.veiculo.getRenavam());
            return identificacao;
        } catch (Exception e) {
            return UUID.randomUUID().toString();
        }
    }
}