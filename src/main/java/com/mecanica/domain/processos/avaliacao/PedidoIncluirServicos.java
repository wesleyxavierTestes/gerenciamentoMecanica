package com.mecanica.domain.processos.avaliacao;

import java.util.List;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.entities.servico.IServico;
import com.mecanica.domain.entities.servico.ItemServico;
import com.mecanica.domain.entities.servico.ServicoOrcamento;
import com.mecanica.domain.entities.servico.ServicoOrdemServico;
import com.mecanica.domain.enuns.produto.EnumTipoProduto;

import lombok.Getter;

/**
 * Inclui Servicos no Orçamento ou Ordem de Serviço
 */
@Getter
public class PedidoIncluirServicos<T extends AbstractOrdemServico> {

    private T ordemServico;

    public PedidoIncluirServicos(T ordemServico) {
        this.ordemServico = ordemServico;
    }

    public <Y extends IServico> void incluirServicos(List<Y> servicos) {
        for (IServico servico : servicos) {
            boolean isOrdemServico = this.ordemServico instanceof OrdemServico && servico instanceof ServicoOrdemServico;
            boolean isOrcamento = this.ordemServico instanceof Orcamento && servico instanceof ServicoOrcamento;
            
            if (!isOrdemServico && !isOrcamento) {
                throw new RegraBaseException(String.format("Servico código %s tem tipo Inválido para operação", servico.getCodigo()));
            }

            servico.setTipoProduto(EnumTipoProduto.Servico);
            this.ordemServico.setServicoItem(servico);

            this.ordemServico.setValor(this.ordemServico.getSomaValor());
            this.ordemServico.setValorTotal(this.ordemServico.getSomaValorTotal());
        }
    }

	public void incluirItemServicos(List<ItemServico> servicos) {
        for (IServico servico : servicos) {
            boolean isOrdemServico = this.ordemServico instanceof OrdemServico && servico instanceof ItemServico;
            boolean isOrcamento = this.ordemServico instanceof Orcamento && servico instanceof ItemServico;
            
            if (!isOrdemServico && !isOrcamento) {
                throw new RegraBaseException(String.format("Item de Servico código %s tem tipo Inválido para operação", servico.getCodigo()));
            }

            servico.setTipoProduto(EnumTipoProduto.ItemServico);
            this.ordemServico.setServicoItem(servico);

            this.ordemServico.setValor(this.ordemServico.getSomaValor());
            this.ordemServico.setValorTotal(this.ordemServico.getSomaValorTotal());
        }
	}
}