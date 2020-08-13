
package com.mecanica.infra.repositorys.mecanico;

import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.infra.repositorys.funcionario.IBaseFuncionarioRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IMecanicoRepository extends IBaseFuncionarioRepository<Mecanico> {

}