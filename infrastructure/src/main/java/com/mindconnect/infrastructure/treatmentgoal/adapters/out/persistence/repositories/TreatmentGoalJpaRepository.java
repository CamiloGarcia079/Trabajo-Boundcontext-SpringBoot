package com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;

/**
 * Repositorio de Spring Data para treatment_goals. Spring genera la implementación.
 */
public interface TreatmentGoalJpaRepository extends JpaRepository<TreatmentGoalJpaEntity, UUID> {
}
