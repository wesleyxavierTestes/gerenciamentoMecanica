package com.mecanica.application.validation;

import java.util.Objects;
import java.util.UUID;

import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.services.BaseService;

public abstract class BaseValidations<T extends BaseEntity, Y extends BaseService> {
    
    protected final Y _service;

    public abstract String getNome();

    public BaseValidations(Y service) {
        _service = service;
    }

    public T findValidExistsById(String clienteId) {
        T entity = (T)this._service.find(UUID.fromString(clienteId));
        if (!Objects.nonNull(entity))
            throw new ValidacaoControllerBaseException(this.getNome()+" inexistênte");

        return entity;
    }
}