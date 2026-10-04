package com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;

/**
 * Repositorio de Spring Data para encounter_statuses. Spring genera la implementación.
 */
public interface EncounterStatusJpaRepository extends JpaRepository<EncounterStatusJpaEntity, UUID> {
}
