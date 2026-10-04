package com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;

/**
 * Repositorio de Spring Data para risk_assessments. Spring genera la implementación.
 */
public interface RiskAssessmentJpaRepository extends JpaRepository<RiskAssessmentJpaEntity, UUID> {
}
