package com.mecanica.domain.processos.servicos;

import java.math.BigDecimal;
import java.util.List;

import com.mecanica.domain.entities.financeiro.FinanceiroEntrada;
import com.mecanica.domain.entities.financeiro.FinanceiroSaida;
import com.mecanica.domain.entities.financeiro.IFinanceiro;
import com.mecanica.domain.entities.ordemServico.ordemServico.OrdemServico;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;

public class ReceberPagamento extends ServiceProcessos<OrdemServico> {

    public ReceberPagamento(OrdemServico ordemServico) {
        super(ordemServico);
    }

    public void receberPagamento(FinanceiroEntrada entrada) {        
        configureValores(entrada);

        List<IFinanceiro> list = addEntradaList(entrada);

        BigDecimal valorRestrante = calcularValoresRestante(list);

        boolean pago = validarPago(valorRestrante);

        configurarValorDevolucao(list, valorRestrante, pago);
    }

    private void configurarValorDevolucao(List<IFinanceiro> list, BigDecimal valorRestrante, boolean pago) {
        if (!pago && valorRestrante.doubleValue() > 0) {
            FinanceiroSaida devolucao = new FinanceiroSaida();
            list.add(devolucao);
        }
    }

    private boolean validarPago(BigDecimal valorRestrante) {
        boolean pago = (BigDecimal.ZERO.equals(valorRestrante));
        ordemServico.setPago(pago);
        return pago;
    }

    private BigDecimal calcularValoresRestante(List<IFinanceiro> list) {
        BigDecimal valorTotal = list.stream().map(IFinanceiro::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valorRestrante = valorTotal.subtract(ordemServico.getValor());
        return valorRestrante;
    }

    private List<IFinanceiro> addEntradaList(FinanceiroEntrada entrada) {
        List<IFinanceiro> list = ordemServico.getItensFinanceiro();
        list.add(entrada);
        return list;
    }

    private void configureValores(FinanceiroEntrada entrada) {
        ordemServico.calcularValorTotal();
        entrada.configure();
    }
}