package com.mindconnect.infrastructure.aimodel.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;

/**
 * Repositorio de Spring Data para ai_models. Spring genera la implementación.
 */
public interface AiModelJpaRepository extends JpaRepository<AiModelJpaEntity, UUID> {
}
