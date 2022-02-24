package com.ua.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import com.ua.persistence.entity.BaseEntity;

@NoRepositoryBean
public interface AbstractRepository<ENTITY extends BaseEntity> extends JpaRepository<ENTITY, Long>, JpaSpecificationExecutor<ENTITY> {
}
