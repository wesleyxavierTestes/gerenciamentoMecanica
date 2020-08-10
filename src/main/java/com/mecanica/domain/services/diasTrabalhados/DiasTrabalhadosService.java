package com.mecanica.domain.services.diasTrabalhados;

import com.mecanica.domain.services.BaseService;
import com.mecanica.data.repositorys.diasTrabalhados.IDiasTrabalhadosRepository;
import com.mecanica.domain.entities.diasTrabalhados.DiasTrabalhados;

import org.springframework.stereotype.Service;

@Service
public class DiasTrabalhadosService extends BaseService<DiasTrabalhados, IDiasTrabalhadosRepository> {

    public DiasTrabalhadosService(IDiasTrabalhadosRepository repository) {
        super(repository);
    }
}