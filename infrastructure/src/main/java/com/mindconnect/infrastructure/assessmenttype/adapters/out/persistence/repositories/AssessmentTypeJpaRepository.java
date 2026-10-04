package com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;

/**
 * Repositorio de Spring Data para assessment_types. Spring genera la implementación.
 */
public interface AssessmentTypeJpaRepository extends JpaRepository<AssessmentTypeJpaEntity, UUID> {
}
