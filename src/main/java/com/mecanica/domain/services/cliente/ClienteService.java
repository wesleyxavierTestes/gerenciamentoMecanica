
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

	public Page<Cliente> findAllByNomeContainsIgnoreCase(String nome, int page) {
		return this.repository.findAllByNomeContainingIgnoreCase(nome, PageRequest.of((page - 1), 10));
	}

	public Cliente findByCnpjEquals(String cnpj) {
		return this.repository.findByCnpjEquals(cnpj);
	}

	public Cliente findByCpfEquals(String cpf) {
		return this.repository.findByCpfEquals(cpf);
	}
}