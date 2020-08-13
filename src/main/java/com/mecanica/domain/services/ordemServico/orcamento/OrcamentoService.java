package com.mecanica.domain.services.ordemServico.orcamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.servico.ServicoOrcamento;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.enuns.EnumDiagnosticoAvaliacao;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.processos.avaliacao.AvaliacaoVeiculoSemConcerto;
import com.mecanica.domain.processos.avaliacao.ClienteAceitarOrcamento;
import com.mecanica.domain.processos.avaliacao.ClienteNegarOrcamento;
import com.mecanica.domain.processos.avaliacao.FazerAvaliacao;
import com.mecanica.domain.processos.avaliacao.OrcamentoEsperaAvaliacao;
import com.mecanica.domain.processos.avaliacao.PedidoIncluirServicos;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.ordemServico.orcamento.IOrcamentoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService extends BaseService<Orcamento, IOrcamentoRepository> {

    public OrcamentoService(final IOrcamentoRepository repository) {
        super(repository);
    }

    /**
     * Cria um novo orçmento com pedido pendente para avaliação do mecânico
     * 
     * @param cliente
     * @param veiculo
     * @return
     */
    public Orcamento criarPedidoAvaliacao(final IFuncionario atendente, final Cliente cliente, final Veiculo veiculo,
            final String causas) {
        final OrcamentoEsperaAvaliacao orcamentoEsperaAvaliacao = new OrcamentoEsperaAvaliacao(atendente, cliente,
                veiculo, causas);

        orcamentoEsperaAvaliacao.criarPedidoAvaliacao();

        return orcamentoEsperaAvaliacao.getordemServico();
    }

    /**
     * Busca O orçamento prévio Avalia o mesmo
     * 
     * @param orcamentoId
     * @param avaliacao
     * @param mecanico
     * @return
     */
    public Orcamento configurarAvaliacao(Orcamento entity, @Valid final Avaliacao avaliacao, final Mecanico mecanico) {

        if (entity.getSituacao() != EnumSituacaoOrcamento.Aguardando) {
            throw new RegraBaseException("Orçamento Finalizado");
        }

        final FazerAvaliacao fazerAvaliacao = new FazerAvaliacao(entity);

        fazerAvaliacao.incluirDados(mecanico, avaliacao);

        return fazerAvaliacao.getordemServico();
    }

    /**
     * Seta Serviços no Orçamento
     */
    public Orcamento configurarServicos(final Orcamento entity, final List<ServicoOrcamento> servicos) {
        final PedidoIncluirServicos<Orcamento> pedidoIncluirServicos = new PedidoIncluirServicos<>(entity);

        pedidoIncluirServicos.incluirServicos(servicos);

        return pedidoIncluirServicos.getOrdemServico();
    }

    public Page<Orcamento> findAllSituacao(EnumSituacaoOrcamento situacao, int page) {
        return this.repository.findAllBySituacao(situacao.name(), PageRequest.of(page - 1, 10));
    }

    /**
     * Configura situação para Avaliado quando Avaliação for diferente de Análise e
     * ainda não houver data de finalização
     * 
     * @param entity
     * @return
     */
    public Orcamento configurarSituacaoOrcamento(Orcamento entity) {
        Avaliacao avaliacao = entity.getAvaliacao();

        if (avaliacao.getDiagnostico() != EnumDiagnosticoAvaliacao.Analise
                && !Objects.nonNull(avaliacao.getDataFinalizacao())) {
            entity.setSituacao(EnumSituacaoOrcamento.Avaliado);

            avaliacao.setDataFinalizacao(LocalDateTime.now());

            entity.setAvaliacao(avaliacao);
        }

        return entity;
    }

    public Orcamento findByIdentificacao(String identificacao) {
        Orcamento entity = this.repository.findByIdentificacao(identificacao);
        return entity;
    }

    public Page<Orcamento> findAllByClienteIdOrNomeOrCpfOrCnpj(String clienteNome,
            String clienteCpfCnpj, int page) {

        return this.repository.findAllByClienteIdOrNomeOrCpfOrCnpj(clienteNome, clienteCpfCnpj,
                PageRequest.of((page - 1), 10));
    }

    public Orcamento aceitarOrcamento(String identificacao) {
        Orcamento entity = this.findByIdentificacao(identificacao);

        ClienteAceitarOrcamento aceite = new ClienteAceitarOrcamento(entity);

        return aceite.getordemServico();
    }

    public Orcamento negarOrcamento(String identificacao) {
        Orcamento entity = this.findByIdentificacao(identificacao);

        ClienteNegarOrcamento negar = new ClienteNegarOrcamento(entity);

        return negar.getordemServico();
    }

	public Orcamento veiculoSemConcerto(String identificacao, Mecanico mecanico) {
        Orcamento entity = this.findByIdentificacao(identificacao);

        AvaliacaoVeiculoSemConcerto semConcerto = new AvaliacaoVeiculoSemConcerto(entity);

        semConcerto.veiculoSemConcerto(mecanico);

		return semConcerto.getordemServico();
	}
}
