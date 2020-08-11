
package com.mecanica.domain.services.funcionario;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.funcionario.IFuncionarioRepository;
import com.mecanica.domain.entities.funcionario.Funcionario;

import org.springframework.stereotype.Service;

@Service
public class FuncionarioService extends BaseService<Funcionario, IFuncionarioRepository> {

    public FuncionarioService(IFuncionarioRepository repository) {
        super(repository);
    }
}