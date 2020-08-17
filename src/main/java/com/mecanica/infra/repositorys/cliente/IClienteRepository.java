
package com.mecanica.infra.repositorys.cliente;

import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends IBaseRepository<Cliente> {

	Page<Cliente> findAllByNomeContainingIgnoreCase(String nome, Pageable of);

	Cliente findByCpfEquals(String cpf);

	Cliente findByCnpjEquals(String cnpj);
}