package com.mecanica.domain.services.veiculo;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.veiculo.IVeiculoRepository;

import java.util.Optional;

import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.veiculo.Veiculo;

import org.springframework.stereotype.Service;

@Service
public class VeiculoService extends BaseService<Veiculo, IVeiculoRepository> {

    public VeiculoService(IVeiculoRepository repository) {
        super(repository);
    }

    public Veiculo save(Veiculo entity, Cliente cliente) {
        
        entity.setCliente(cliente);

        super.save(entity);

        return entity;
    }

    public Veiculo update(Veiculo entity, Cliente cliente) {
        Optional<Veiculo> optionalEntityUpdate = repository.findById(entity.getId());
        if (!optionalEntityUpdate.isPresent()) {
            return null;
        }

        Veiculo entityUpdate = optionalEntityUpdate.get();
        if (!entityUpdate.getCliente().getId().equals(cliente.getId())) {
            return null;
        }

        entity.setCliente(entityUpdate.getCliente());

        super.update(entity, entityUpdate);

        return entity;
    }
}