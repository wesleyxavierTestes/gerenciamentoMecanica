package com.mecanica.application.applicationServices.funcionario;

import java.util.Objects;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.services.funcionario.FuncionarioService;

import org.springframework.stereotype.Service;

@Service
public class FuncionarioValidations extends BaseValidations<Funcionario, FuncionarioService> {

    public FuncionarioValidations(FuncionarioService serviceFuncionario) {
        super(serviceFuncionario);
    }

    public Funcionario findValidExistsByCpf(String cpf) {
        Funcionario entity = this._service.findByCpf(cpf);
        if (!Objects.nonNull(entity))
            throw new ValidacaoControllerBaseException(this.getNome()+" inexistênte");

        return entity;
    }
}