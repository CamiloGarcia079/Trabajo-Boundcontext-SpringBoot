package com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;

/**
 * Repositorio de Spring Data para medication_routes. Spring genera la implementación.
 */
public interface MedicationRouteJpaRepository extends JpaRepository<MedicationRouteJpaEntity, UUID> {
}
