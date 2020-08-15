package com.mecanica.application.applicationServices;

import java.util.Objects;
import java.util.UUID;

import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.services.BaseService;

import org.springframework.data.domain.Page;

public abstract class BaseValidations<T extends BaseEntity, Y extends BaseService<T, ?>> {

  protected final Y _service;

  public String getNome() {
      String name = this.getClass().getName();
      String replace = name.replace("Validations", "");
      return replace.substring(replace.lastIndexOf(".") + 1);
  }

  public BaseValidations(Y service) {
    _service = service;
  }

  public T findValidExistsById(String clienteId) {
    T entity = (T) this._service.find(UUID.fromString(clienteId));
    if (!Objects.nonNull(entity))
      throw new ValidacaoControllerBaseException(this.getNome() + " inexistênte");

    return entity;
  }

  public Page<T> findAllFilter(T entityExample, int page) {
    return this._service.findAllFilter(entityExample, page);
  }

  public Page<T> findAll(int page) {
    return this._service.findAll(page);
  }

  public T find(UUID id) {
    return this._service.find(id);
  }

  public T save(T entity) {
    return this._service.save(entity);
  }

  public T update(T entity, T entityUpdate) {
    return this._service.update(entity, entityUpdate);
  }

  public T update(T entity) {
    return this._service.update(entity);
  }
}