
package com.mecanica.infra.repositorys.mecanico;

import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IMecanicoRepository extends IBaseRepository<Mecanico> {

}