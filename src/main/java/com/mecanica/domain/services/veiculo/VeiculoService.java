package com.mecanica.domain.services.veiculo;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.veiculo.IVeiculoRepository;
import com.mecanica.domain.entities.veiculo.Veiculo;

import org.springframework.stereotype.Service;

@Service
public class VeiculoService extends BaseService<Veiculo, IVeiculoRepository> {

    public VeiculoService(IVeiculoRepository repository) {
        super(repository);
    }
}