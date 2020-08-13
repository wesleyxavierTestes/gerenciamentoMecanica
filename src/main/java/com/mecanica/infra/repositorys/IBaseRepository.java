package com.mecanica.infra.repositorys;

import java.util.UUID;

import com.mecanica.domain.entities.IBaseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseRepository<T extends IBaseEntity> extends JpaRepository<T, UUID> {

}