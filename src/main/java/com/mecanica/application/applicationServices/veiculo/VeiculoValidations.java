package com.mecanica.application.applicationServices.veiculo;

import java.util.Objects;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.veiculo.VeiculoService;

import org.springframework.data.domain.Page;
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

    public Page<Veiculo> findAllByClienteId(String clienteId, int page) {
        return this._service.findAllByClienteId(clienteId, page);
    }

    public Veiculo save(Veiculo entity, Cliente cliente) {
        return this._service.save(entity, cliente);
    }
}