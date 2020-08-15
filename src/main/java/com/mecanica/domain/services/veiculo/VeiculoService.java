package com.mecanica.domain.services.veiculo;

import java.util.UUID;

import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.veiculo.IVeiculoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	public Veiculo findByRenavam(String veiculoRenavam) {
		return this.repository.findByRenavamEquals(veiculoRenavam);
	}

	public Page<Veiculo> findAllByClienteId(String clienteId, int page) {
		return this.repository.findAllByClienteId(UUID.fromString(clienteId), PageRequest.of((page - 1), 10));
	}
}