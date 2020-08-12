package com.mecanica.domain.services.ordemServico.orcamento;

import java.util.UUID;

import javax.validation.Valid;

import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.IFuncionario;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.servico.command.OrcamentoEsperaAvaliacao;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.ordemServico.orcamento.IOrcamentoRepository;

import org.springframework.stereotype.Service;

@Service
public class OrcamentoService extends BaseService<Orcamento, IOrcamentoRepository> {

    public OrcamentoService(IOrcamentoRepository repository) {
        super(repository);
    }

    /**
     * Cria um novo orçmento com pedido pendente para avaliação do mecânico
     * @param cliente
     * @param veiculo
     * @return
     */
    public Orcamento criarPedidoAvaliacao(IFuncionario atendente, Cliente cliente, Veiculo veiculo, String causas) {
        OrcamentoEsperaAvaliacao orcamentoEsperaAvaliacao = 
                    new OrcamentoEsperaAvaliacao(atendente, cliente, veiculo, causas);
        
        Orcamento orcamento = orcamentoEsperaAvaliacao.criarPedidoAvaliacao();
    
        orcamentoEsperaAvaliacao.Acao(orcamento);
        
        return orcamento;
    }

	public Orcamento configurarAvaliacao(String orcamentoId, @Valid Avaliacao avaliacao, Mecanico mecanico) {
        Orcamento entity = this.find(UUID.fromString(orcamentoId));

        avaliacao.setMecanico(mecanico);
        entity.setAvaliacao(avaliacao);

        return null;
	}
}
