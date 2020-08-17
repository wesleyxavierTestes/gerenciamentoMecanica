package com.mecanica.domain.processos.avaliacao;

import java.time.LocalDateTime;

import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.processos.baseDefault.ServiceProcessos;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;

/**
 * 
 */
public class OrcamentoEsperaAvaliacao extends ServiceProcessos<Orcamento> {

    private Cliente cliente;

    private Veiculo veiculo;

    private String descricaoProblema;

    private IFuncionario atendente;

    public OrcamentoEsperaAvaliacao(final Orcamento ordemServico) {
        super(ordemServico);
    }

    public OrcamentoEsperaAvaliacao(final IFuncionario atendente, final Cliente cliente, final Veiculo veiculo,
            final String descricaoProblema) {
        super(new Orcamento());
        this.atendente = atendente;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.descricaoProblema = descricaoProblema;
    }

    /**
     * Orçamento prévio para aguardar Avaliação do mecânico
     * 
     * @return
     */
    public void criarPedidoAvaliacao() {

        ordemServico.setDescricaoProblema(descricaoProblema);
        ordemServico.setSituacao(EnumSituacaoOrcamento.Aguardando);
        ordemServico.setAvaliacao(gerarNovaAvaliacaoDeEspera());
        ordemServico.setAtendente(atendente);
        ordemServico.setCliente(cliente);
        ordemServico.setVeiculo(veiculo);
       
        ordemServico.setIdentificacao(ordemServico.getCustoIdentificacao());
    }

    /**
     * Aguarda mecânico fazer avaliação
     * 
     * @return
     */
    private Avaliacao gerarNovaAvaliacaoDeEspera() {
        final Avaliacao avaliacao = new Avaliacao();

        avaliacao.setDataInicial(LocalDateTime.now());
        avaliacao.setMecanico(null);
        avaliacao.setDiagnostico(EnumDiagnosticoAvaliacao.Analise);

        return avaliacao;
    }

}