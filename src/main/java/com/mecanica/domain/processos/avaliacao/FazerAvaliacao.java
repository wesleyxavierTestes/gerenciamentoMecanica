package com.mecanica.domain.processos.avaliacao;

import java.time.LocalDate;

import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class FazerAvaliacao extends ServiceProcessos<Orcamento>  {

    public FazerAvaliacao(Orcamento ordemServico) {
        super(ordemServico);
    }

	public void incluirDados(int dias, LocalDate dataPrevisaoInicio, Mecanico mecanico, Avaliacao avaliacao) {
        ordemServico.setDiasEstimadoServico(dias);
        ordemServico.setDataPrevisaoInicio(dataPrevisaoInicio);
        avaliacao.setMecanico(mecanico);
        ordemServico.setAvaliacao(avaliacao);

        this.ordemServico.setValor(this.ordemServico.getSomaValor());
        this.ordemServico.setValorTotal(this.ordemServico.getSomaValorTotal());
	}
}