package com.mindconnect.infrastructure.priority.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.priority.adapters.out.persistence.entity.PriorityJpaEntity;

/**
 * Repositorio de Spring Data para priorities. Spring genera la implementación.
 */
public interface PriorityJpaRepository extends JpaRepository<PriorityJpaEntity, UUID> {
}
