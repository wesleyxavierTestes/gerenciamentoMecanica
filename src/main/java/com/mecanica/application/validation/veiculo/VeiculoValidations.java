package com.mecanica.application.validation.veiculo;

import com.mecanica.application.validation.BaseValidations;
import com.mecanica.domain.entities.veiculo.Veiculo;
import com.mecanica.domain.services.veiculo.VeiculoService;

import org.springframework.stereotype.Service;

@Service
public class VeiculoValidations extends BaseValidations<Veiculo, VeiculoService> {

    public VeiculoValidations(VeiculoService serviceVeiculo) {
        super(serviceVeiculo);
    }

    @Override
    public String getNome() {
        return "Veiculo";
    }
}