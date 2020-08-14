
package com.mecanica.infra.repositorys.funcionario;

import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseFuncionarioRepository<T extends Funcionario> extends IBaseRepository<T> {

    T findByCpfEquals(String cpf);
}