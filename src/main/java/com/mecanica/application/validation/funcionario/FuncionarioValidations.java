package com.mecanica.application.validation.funcionario;

import com.mecanica.application.validation.BaseValidations;
import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.services.funcionario.FuncionarioService;

import org.springframework.stereotype.Service;

@Service
public class FuncionarioValidations extends BaseValidations<Funcionario, FuncionarioService> {

    public FuncionarioValidations(FuncionarioService serviceFuncionario) {
        super(serviceFuncionario);
    }

    @Override
    public String getNome() {
        return "Funcionario";
    }
}