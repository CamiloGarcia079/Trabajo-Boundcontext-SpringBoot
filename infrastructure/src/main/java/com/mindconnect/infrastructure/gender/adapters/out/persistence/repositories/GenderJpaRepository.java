package com.mindconnect.infrastructure.gender.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;

/**
 * Repositorio de Spring Data para genders. Spring genera la implementación.
 */
public interface GenderJpaRepository extends JpaRepository<GenderJpaEntity, UUID> {
}
