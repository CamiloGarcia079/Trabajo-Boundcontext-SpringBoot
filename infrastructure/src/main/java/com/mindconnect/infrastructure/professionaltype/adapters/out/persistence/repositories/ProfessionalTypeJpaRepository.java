package com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;

/**
 * Repositorio de Spring Data para professional_types. Spring genera la implementación.
 */
public interface ProfessionalTypeJpaRepository extends JpaRepository<ProfessionalTypeJpaEntity, UUID> {
}
