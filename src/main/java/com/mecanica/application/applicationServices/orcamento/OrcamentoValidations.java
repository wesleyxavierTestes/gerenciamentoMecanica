package com.mecanica.application.applicationServices.orcamento;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.application.applicationServices.cliente.ClienteValidations;
import com.mecanica.application.applicationServices.funcionario.FuncionarioValidations;
import com.mecanica.application.applicationServices.mecanico.MecanicoValidations;
import com.mecanica.application.applicationServices.veiculo.VeiculoValidations;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.servico.ItemServico;
import com.mecanica.domain.entities.servico.ServicoOrcamento;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.enuns.EnumSituacaoOrcamento;
import com.mecanica.domain.services.ordemServico.orcamento.OrcamentoService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoValidations extends BaseValidations<Orcamento, OrcamentoService> {

    private final FuncionarioValidations _serviceFuncionario;
    private final MecanicoValidations _serviceMecanico;
    private final ClienteValidations _serviceCliente;
    private final VeiculoValidations _serviceVeiculo;

    public OrcamentoValidations(OrcamentoService serviceOrcamento, ClienteValidations serviceCliente,
            VeiculoValidations serviceVeiculo, FuncionarioValidations serviceFuncionario,
            MecanicoValidations serviceMecanico) {
        super(serviceOrcamento);
        _serviceCliente = serviceCliente;
        _serviceVeiculo = serviceVeiculo;
        _serviceFuncionario = serviceFuncionario;
        _serviceMecanico = serviceMecanico;
    }

    public void valida(Orcamento entity) {

        Avaliacao avaliacao = entity.getAvaliacao();
        Mecanico mecanico = avaliacao.getMecanico();
        if (mecanico != null) {
            mecanico = _serviceMecanico.findValidExistsById(mecanico.getId().toString());
            avaliacao.setMecanico(mecanico);
        }

        IFuncionario atendente = entity.getAtendente();
        atendente = _serviceFuncionario.findValidExistsById(atendente.getId().toString());
        entity.setAtendente(atendente);

        Cliente cliente = entity.getCliente();
        cliente = _serviceCliente.findValidExistsById(cliente.getId().toString());
        entity.setCliente(cliente);

        Veiculo veiculo = entity.getVeiculo();
        veiculo = _serviceVeiculo.findValidExistsById(veiculo.getId().toString());
        entity.setVeiculo(veiculo);
    }

    public Orcamento findValidExistsByIdentificacao(String identificacao) {
        try {
            Orcamento entity = this._service.findByIdentificacao(identificacao);
            if (!Objects.nonNull(entity))
                throw new ValidacaoControllerBaseException(this.getNome() + " inexistênte");
            return entity;
        } catch (Exception e) {
            throw new ValidacaoControllerBaseException(this.getNome() + " inexistênte");
        }
    }

	public Page<Orcamento> findAllBySituacaoEquals(EnumSituacaoOrcamento situacao, int page) {
		return this._service.findAllBySituacaoEquals(situacao, page);
	}

	public Orcamento criarPedidoAvaliacao(Funcionario atendente, Cliente cliente, Veiculo veiculo,
			String descricaoProblema) {
		return this._service.criarPedidoAvaliacao(atendente, cliente, veiculo, descricaoProblema);
	}

	public Orcamento configurarAvaliacao(Orcamento entity, Avaliacao avaliacao, Mecanico mecanico, int dias,
			LocalDate dataPrevisaoInicio) {
		return this._service.configurarAvaliacao(entity, avaliacao, mecanico, dias, dataPrevisaoInicio);
	}

	public Orcamento configurarServicos(Orcamento entity, List<ServicoOrcamento> servicos) {
		return this._service.configurarServicos(entity, servicos);
	}

	public Orcamento configurarItemServico(Orcamento entity, List<ItemServico> servicos) {
		return this._service.configurarItemServico(entity, servicos);
	}

	public Orcamento configurarSituacaoOrcamento(Orcamento entity) {
		return this.configurarSituacaoOrcamento(entity);
	}

	public Orcamento veiculoSemConcerto(String identificacao, Mecanico mecanico) {
		return this.veiculoSemConcerto(identificacao, mecanico);
	}
}