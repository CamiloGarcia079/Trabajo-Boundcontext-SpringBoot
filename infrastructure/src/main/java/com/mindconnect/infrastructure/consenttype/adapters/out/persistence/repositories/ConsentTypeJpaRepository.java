package com.mindconnect.infrastructure.consenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;

/**
 * Repositorio de Spring Data para consent_types. Spring genera la implementación.
 */
public interface ConsentTypeJpaRepository extends JpaRepository<ConsentTypeJpaEntity, UUID> {
}
