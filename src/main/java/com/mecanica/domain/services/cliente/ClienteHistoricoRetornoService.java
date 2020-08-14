
package com.mecanica.domain.services.cliente;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.cliente.IClienteHistoricoRetornoRepository;

import com.mecanica.domain.entities.cliente.ClienteHistoricoRetorno;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.processos.avaliacao.InformarOrcamentoCliente;

import org.springframework.stereotype.Service;

@Service
public class ClienteHistoricoRetornoService
        extends BaseService<ClienteHistoricoRetorno, IClienteHistoricoRetornoRepository> {

    public ClienteHistoricoRetornoService(IClienteHistoricoRetornoRepository repository) {
        super(repository);
    }

    /**
     * Registra o contato realizado com o cliente
     * @param atendente
     * @param orcamento
     * @param clienteHistoricoRetorno
     * @return
     */
    public ClienteHistoricoRetorno informarCliente(Funcionario atendente, Orcamento orcamento,
            ClienteHistoricoRetorno clienteHistoricoRetorno) {
        return new InformarOrcamentoCliente(orcamento).incluirOrcamento(atendente, clienteHistoricoRetorno);
    }
}