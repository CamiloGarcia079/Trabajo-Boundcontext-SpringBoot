package com.mindconnect.infrastructure.country.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;

/**
 * Repositorio de Spring Data para countries. Spring genera la implementación.
 */
public interface CountryJpaRepository extends JpaRepository<CountryJpaEntity, UUID> {
}
