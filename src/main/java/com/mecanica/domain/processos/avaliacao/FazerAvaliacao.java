package com.mecanica.domain.processos.avaliacao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class FazerAvaliacao extends ServiceProcessos<Orcamento>  {

    public FazerAvaliacao(Orcamento ordemServico) {
        super(ordemServico);
    }

	public void incluirDados(LocalDate dataPrevisaoInicio,  LocalDate dataPrevisaoFinalizacao, Mecanico mecanico, Avaliacao avaliacao) {
        ordemServico.setDiasEstimadoServico((int)ChronoUnit.DAYS.between(dataPrevisaoInicio, dataPrevisaoFinalizacao));
        ordemServico.setDataPrevisaoInicio(dataPrevisaoInicio);
        ordemServico.setDataPrevisaoFinalizacao(dataPrevisaoFinalizacao);
        avaliacao.setMecanico(mecanico);
        ordemServico.setAvaliacao(avaliacao);

        this.ordemServico.calcularValorTotal();
	}
}