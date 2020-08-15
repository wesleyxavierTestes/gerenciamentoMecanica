package com.mecanica.application.applicationServices.mecanico;

import java.util.Objects;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.mecanico.Mecanico;
import com.mecanica.domain.services.mecanico.MecanicoService;

import org.springframework.stereotype.Service;

@Service
public class MecanicoValidations extends BaseValidations<Mecanico, MecanicoService> {

    public MecanicoValidations(MecanicoService serviceMecanico) {
        super(serviceMecanico);
    }

    public Mecanico findValidExistsByCpf(String cpf) {
        Mecanico entity = this._service.findByCpf(cpf);
        if (!Objects.nonNull(entity))
            throw new ValidacaoControllerBaseException(this.getNome()+" inexistênte");

        return entity;
    }
}