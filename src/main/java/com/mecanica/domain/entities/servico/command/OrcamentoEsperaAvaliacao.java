package com.mecanica.domain.entities.servico.command;

import java.time.LocalDateTime;

import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;

public class OrcamentoEsperaAvaliacao extends ServiceCommand<Orcamento> {

    private Cliente cliente;
    private Veiculo veiculo;
    private String causas;
    private IFuncionario atendente;

    public OrcamentoEsperaAvaliacao(Orcamento ordemServico) {
        super(ordemServico);
    }

    public OrcamentoEsperaAvaliacao(IFuncionario atendente, Cliente cliente, Veiculo veiculo, String causas) {
        super(null);
        this.atendente = atendente;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.causas = causas;
    }

    /**
     * Seta na fila de ações para fazer
     */
    @Override
    public boolean Acao(Orcamento ordemServico) {
        ServicosInvoker.setComando(new FazerAvaliacao(ordemServico));
        return false;
    }

    public Orcamento criarPedidoAvaliacao() {
        return gerarOrcamentoDeEspera();
    }

    /**
     * Orçamento prévio para aguardar Avaliação do mecânico
     * @return
     */
    private Orcamento gerarOrcamentoDeEspera() {
        Orcamento orcamento = new Orcamento();

        orcamento.setDescricaoProblema(causas);
        orcamento.setSituacao(EnumSituacaoOrcamento.Aguardando);
        orcamento.setAvaliacao(gerarNovaAvaliacaoDeEspera());
        orcamento.setAtendente(atendente);
        orcamento.setCliente(cliente);
        orcamento.setVeiculo(veiculo);
        
        return orcamento;
    }

    /**
     * Aguarda mecânico fazer avaliação
     * @return
     */
    private Avaliacao gerarNovaAvaliacaoDeEspera() {
        Avaliacao avaliacao = new Avaliacao();
        
        avaliacao.setDataInicial(LocalDateTime.now());
        avaliacao.setMecanico(null);
        avaliacao.setDiagnostico(EnumDiagnosticoAvaliacao.Aguardando);

        return avaliacao;
    }

}