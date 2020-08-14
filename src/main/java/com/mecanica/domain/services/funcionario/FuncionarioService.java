
package com.mecanica.domain.services.funcionario;

import com.mecanica.infra.repositorys.funcionario.IFuncionarioRepository;
import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.funcionario.Funcionario;

import org.springframework.stereotype.Service;

@Service
public class FuncionarioService extends BaseFuncionarioService<Funcionario, IFuncionarioRepository> {

    public FuncionarioService(IFuncionarioRepository repository) {
        super(repository);
    }
}