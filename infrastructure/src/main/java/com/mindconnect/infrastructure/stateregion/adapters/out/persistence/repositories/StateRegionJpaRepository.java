package com.mindconnect.infrastructure.stateregion.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;

/**
 * Repositorio de Spring Data para state_regions. Spring genera la implementación.
 */
public interface StateRegionJpaRepository extends JpaRepository<StateRegionJpaEntity, UUID> {
}
