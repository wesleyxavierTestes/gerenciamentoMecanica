package com.mecanica.application.applicationServices.cliente;

import java.util.Objects;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.services.cliente.ClienteService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ClienteValidations extends BaseValidations<Cliente, ClienteService> {

    public ClienteValidations(ClienteService serviceCliente) {
        super(serviceCliente);
    }

    public Page<Cliente> findAllByNomeContainsIgnoreCase(String nome, int page) {
        return this._service.findAllByNomeContainsIgnoreCase(nome, page);
    }

    public void validExistsByCpfCpj(Cliente entity) {
        Cliente entityExists = null;

        String cpf = entity.getCpf();
        if (Objects.nonNull(cpf)) {
            entityExists = this._service.findByCpfEquals(cpf);
            if (Objects.nonNull(entityExists))
                throw new ValidacaoControllerBaseException("Cpf duplicado");
        }

        String cnpj = entity.getCnpj();
        if (Objects.nonNull(cnpj)) {
            entityExists = this._service.findByCnpjEquals(cnpj);
            if (Objects.nonNull(entityExists))
                throw new ValidacaoControllerBaseException("Cnpj duplicado");
        }

        if (!Objects.nonNull(cpf) && !Objects.nonNull(cnpj)) {
                throw new ValidacaoControllerBaseException("Cpf/Cnpj inválido");
        }
    }
}