package com.mecanica.application.validation.veiculo;

import java.util.Objects;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.application.validation.BaseValidations;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.veiculo.VeiculoService;

import org.springframework.stereotype.Service;

@Service
public class VeiculoValidations extends BaseValidations<Veiculo, VeiculoService> {

    public VeiculoValidations(VeiculoService serviceVeiculo) {
        super(serviceVeiculo);
    }

    @Override
    public String getNome() {
        return "Veiculo";
    }

    public Veiculo findValidExistsByRenavam(String veiculoRenavam) {
        Veiculo entity = this._service.findByRenavam(veiculoRenavam);
        if (!Objects.nonNull(entity))
            throw new ValidacaoControllerBaseException(this.getNome() + " inexistênte");

        return entity;
    }

    public Veiculo validaClienteReferenteDoCarro(Veiculo entity, Cliente cliente) {
        Veiculo entityUpdate = this._service.find(entity.getId());
        if (!Objects.nonNull(entityUpdate)) {
            throw new ValidacaoControllerBaseException(this.getNome() + " inexistênte");
        }

        if (!entityUpdate.getCliente().getId().equals(cliente.getId())) {
            throw new RegraBaseException("Carro não pertencente ao cliente informado");
        }

        entity.setCliente(entityUpdate.getCliente());

        return entityUpdate;
    }
}