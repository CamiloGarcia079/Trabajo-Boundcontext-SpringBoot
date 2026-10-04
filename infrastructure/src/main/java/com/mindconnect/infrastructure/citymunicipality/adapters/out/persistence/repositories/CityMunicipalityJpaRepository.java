package com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;

/**
 * Repositorio de Spring Data para city_municipalities. Spring genera la implementación.
 */
public interface CityMunicipalityJpaRepository extends JpaRepository<CityMunicipalityJpaEntity, UUID> {
}
