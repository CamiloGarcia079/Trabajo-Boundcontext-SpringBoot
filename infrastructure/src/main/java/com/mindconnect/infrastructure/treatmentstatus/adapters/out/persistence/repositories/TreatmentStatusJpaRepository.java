package com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.entity.TreatmentStatusJpaEntity;

/**
 * Repositorio de Spring Data para treatment_statuses. Spring genera la implementación.
 */
public interface TreatmentStatusJpaRepository extends JpaRepository<TreatmentStatusJpaEntity, UUID> {
}
