package com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;

/**
 * Repositorio de Spring Data para escalations_statuses. Spring genera la implementación.
 */
public interface EscalationStatusJpaRepository extends JpaRepository<EscalationStatusJpaEntity, UUID> {
}
