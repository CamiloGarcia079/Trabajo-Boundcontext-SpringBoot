package com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;

/**
 * Repositorio de Spring Data para professional_studies. Spring genera la implementación.
 */
public interface ProfessionalStudyJpaRepository extends JpaRepository<ProfessionalStudyJpaEntity, UUID> {
}
