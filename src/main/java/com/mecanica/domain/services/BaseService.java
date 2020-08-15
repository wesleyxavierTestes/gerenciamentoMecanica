package com.mecanica.domain.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.mecanica.application.config.UpdateConfig;
import com.mecanica.domain.entities.IBaseEntity;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.IBaseRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseService<T extends IBaseEntity, Y extends IBaseRepository<T>> {

    protected final Y repository;

    public BaseService(Y repository) {
        this.repository = repository;
    }

    public Page<T> findAll(int page) {
        PageRequest paginacao = PageRequest.of((page - 1), 10);
        
        Page<T> list = this.repository.findAll(paginacao);

        return list;
    }

    public Page<T> findAllFilter(T entityExample, int page) {
        
        Example<T> example = Example.of(entityExample,
                                ExampleMatcher.matching()
                                    .withIgnoreCase()
                                    .withIgnorePaths("id")
                                    .withIgnorePaths("dataCadastro")
                                    .withIgnoreNullValues()
                                    .withStringMatcher(StringMatcher.CONTAINING));

        PageRequest paginacao = PageRequest.of((page - 1), 10);
        
        Page<T> list = this.repository.findAll(example, paginacao);

        return list;
    }

    public T find(UUID id) {
        Optional<T> entity = this.repository.findById(id);

        if (!entity.isPresent()) {
            return null;
        }

        return entity.get();
    }

    @Transactional
    public T save(T entity) {
        
        entity.setDataCadastro(LocalDateTime.now());
        entity = this.repository.save(entity);
        
        return entity;
    }

    @Transactional
    public T update(T entity) {
        Optional<T> optionalEntityUpdate = this.repository.findById(entity.getId());
        if (!optionalEntityUpdate.isPresent()) {
            return null;
        }

        T entityUpdate = optionalEntityUpdate.get();

        UpdateConfig.by(entity, entityUpdate);

        entityUpdate = this.repository.save(entityUpdate);

        return entityUpdate;
    }

    @Transactional
    public T update(T entity, T entityUpdate) {
        UpdateConfig.by(entity, entityUpdate);

        entityUpdate = this.repository.save(entityUpdate);

        return entityUpdate;
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