package com.mecanica.application.validation.orcamento;

import com.mecanica.application.validation.BaseValidations;
import com.mecanica.application.validation.cliente.ClienteValidations;
import com.mecanica.application.validation.funcionario.FuncionarioValidations;
import com.mecanica.application.validation.mecanico.MecanicoValidations;
import com.mecanica.application.validation.veiculo.VeiculoValidations;
import com.mecanica.domain.entities.avaliacao.Avaliacao;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.entities.ordemServico.orcamento.Orcamento;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.ordemServico.orcamento.OrcamentoService;

import org.springframework.stereotype.Service;

@Service
public class OrcamentoValidations extends BaseValidations<Orcamento, OrcamentoService> {

    private final FuncionarioValidations _serviceFuncionario;
    private final MecanicoValidations _serviceMecanico;
    private final ClienteValidations _serviceCliente;
    private final VeiculoValidations _serviceVeiculo;


    public OrcamentoValidations(OrcamentoService serviceOrcamento, 
    ClienteValidations serviceCliente,
            VeiculoValidations serviceVeiculo, FuncionarioValidations serviceFuncionario,
            MecanicoValidations serviceMecanico) {
        super(serviceOrcamento);
        _serviceCliente = serviceCliente;
        _serviceVeiculo = serviceVeiculo;
        _serviceFuncionario = serviceFuncionario;
        _serviceMecanico = serviceMecanico;
    }

    @Override
    public String getNome() {
        return "Orcamento";
    }

    public void valida(Orcamento entity) {
       
        Avaliacao avaliacao = entity.getAvaliacao();
        Mecanico mecanico = _serviceMecanico.findValidExistsById(avaliacao.getMecanico().getId().toString());
        avaliacao.setMecanico(mecanico);

        Funcionario atendente = _serviceFuncionario.findValidExistsById(entity.getAtendente().getId().toString());
        entity.setAtendente(atendente);

        Cliente cliente = _serviceCliente.findValidExistsById(entity.getCliente().getId().toString());
        entity.setCliente(cliente);

        Veiculo veiculo = _serviceVeiculo.findValidExistsByRenavam(entity.getVeiculo().getId().toString());
        entity.setVeiculo(veiculo);
    }
}