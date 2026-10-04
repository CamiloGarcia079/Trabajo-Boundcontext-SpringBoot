package com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;

/**
 * Repositorio de Spring Data para ai_runs_statuses. Spring genera la implementación.
 */
public interface AiRunStatusJpaRepository extends JpaRepository<AiRunStatusJpaEntity, UUID> {
}
