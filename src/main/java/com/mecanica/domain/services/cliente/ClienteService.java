
package com.mecanica.domain.services.cliente;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.cliente.IClienteRepository;
import com.mecanica.domain.entities.cliente.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends BaseService<Cliente, IClienteRepository> {

    public ClienteService(IClienteRepository repository) {
        super(repository);
    }

	public Page<Cliente> findAllByNomeContains(String nome, int page) {
		return this.repository.findAllByNomeContains(nome, PageRequest.of((page - 1), 10));
	}
}