package com.mindconnect.infrastructure.encountertype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;

/**
 * Repositorio de Spring Data para encounter_types. Spring genera la implementación.
 */
public interface EncounterTypeJpaRepository extends JpaRepository<EncounterTypeJpaEntity, UUID> {
}
