package com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;

/**
 * Repositorio de Spring Data para treatment_plans. Spring genera la implementación.
 */
public interface TreatmentPlanJpaRepository extends JpaRepository<TreatmentPlanJpaEntity, UUID> {
}
