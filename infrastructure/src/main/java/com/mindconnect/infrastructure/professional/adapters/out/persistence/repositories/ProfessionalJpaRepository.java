package com.mindconnect.infrastructure.professional.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;

/**
 * Repositorio de Spring Data para professionals. Spring genera la implementación.
 */
public interface ProfessionalJpaRepository extends JpaRepository<ProfessionalJpaEntity, UUID> {
}
