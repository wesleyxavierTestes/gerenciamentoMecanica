
package com.mecanica.infra.repositorys.funcionario;

import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IFuncionarioRepository extends IBaseRepository<Funcionario> {

}