package com.mindconnect.infrastructure.risklevel.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;

/**
 * Repositorio de Spring Data para risk_levels. Spring genera la implementación.
 */
public interface RiskLevelJpaRepository extends JpaRepository<RiskLevelJpaEntity, UUID> {
}
