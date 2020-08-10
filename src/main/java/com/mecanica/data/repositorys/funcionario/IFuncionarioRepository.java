
package com.mecanica.data.repositorys.funcionario;

import com.mecanica.data.repositorys.IBaseRepository;
import com.mecanica.domain.entities.funcionario.Funcionario;

import org.springframework.stereotype.Repository;

@Repository
public interface IFuncionarioRepository extends IBaseRepository<Funcionario> {

}