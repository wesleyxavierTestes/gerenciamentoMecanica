package com.mecanica.application.validation.mecanico;

import com.mecanica.application.validation.BaseValidations;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.services.mecanico.MecanicoService;

import org.springframework.stereotype.Service;

@Service
public class MecanicoValidations extends BaseValidations<Mecanico, MecanicoService> {

    public MecanicoValidations(MecanicoService serviceMecanico) {
        super(serviceMecanico);
    }

    @Override
    public String getNome() {
        return "Mecânico";
    }
}