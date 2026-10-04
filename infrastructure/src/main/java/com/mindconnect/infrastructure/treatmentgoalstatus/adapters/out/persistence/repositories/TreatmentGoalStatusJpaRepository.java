package com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;

/**
 * Repositorio de Spring Data para treatment_goal_statuses. Spring genera la implementación.
 */
public interface TreatmentGoalStatusJpaRepository extends JpaRepository<TreatmentGoalStatusJpaEntity, UUID> {
}
