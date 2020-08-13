
package com.mecanica.domain.services.mecanico;

import com.mecanica.domain.services.funcionario.BaseFuncionarioService;
import com.mecanica.infra.repositorys.mecanico.IMecanicoRepository;
import com.mecanica.domain.entities.mecanico.Mecanico;

import org.springframework.stereotype.Service;

@Service
public class MecanicoService extends BaseFuncionarioService<Mecanico, IMecanicoRepository> {
    
    public MecanicoService(IMecanicoRepository repository) {
        super(repository);
    }
}
