package com.mecanica.domain.processos.avaliacao;

import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class FazerAvaliacao extends ServiceProcessos<Orcamento>  {

    public FazerAvaliacao(Orcamento ordemServico) {
        super(ordemServico);
    }

	public void incluirDados(int dias, Mecanico mecanico, Avaliacao avaliacao) {
        ordemServico.setDiasEstimadoServico(dias);
        avaliacao.setMecanico(mecanico);
        ordemServico.setAvaliacao(avaliacao);

        this.ordemServico.setValor(this.ordemServico.getSomaValor());
        this.ordemServico.setValorTotal(this.ordemServico.getSomaValorTotal());
	}
}