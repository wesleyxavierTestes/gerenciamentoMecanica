
package com.mecanica.infra.repositorys.funcionario;

import com.mecanica.domain.entities.funcionario.Funcionario;

import org.springframework.stereotype.Repository;

@Repository
public interface IFuncionarioRepository extends IBaseFuncionarioRepository<Funcionario> {

}