package com.mecanica.domain.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.mecanica.domain.entities.BaseEntity;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class BaseService<T extends BaseEntity, Y extends IBaseRepository<T>> {

    protected final Y repository;

    public BaseService(Y repository) {
        this.repository = repository;
    }

    public Page<T> list(int page) {
        PageRequest paginacao = PageRequest.of((page - 1), 10);
        
        Page<T> list = this.repository.findAll(paginacao);

        return list;
    }

    public T find(UUID id) {
        Optional<T> entity = this.repository.findById(id);

        if (!entity.isPresent()) {
            return null;
        }

        return entity.get();
    }

    public T save(T entity) {
        
        entity.setDataCadastro(LocalDateTime.now());
        entity = this.repository.save(entity);
        
        return entity;
    }

    public T update(T entity) {
        Optional<T> entityUpdate = this.repository.findById(entity.getId());
        if (!entityUpdate.isPresent()) {
            return null;
        }

        entity = this.repository.save(entity);

        return entity;
    }

    public T remove(UUID id) {
        Optional<T> entityDelete = this.repository.findById(id);
        if (!entityDelete.isPresent()) {
            return null;
        }
        this.repository.deleteById(id);
        return entityDelete.get();
    }
}