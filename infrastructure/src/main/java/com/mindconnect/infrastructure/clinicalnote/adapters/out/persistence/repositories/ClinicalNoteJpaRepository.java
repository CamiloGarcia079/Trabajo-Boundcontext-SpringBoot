package com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;

/**
 * Repositorio de Spring Data para clinical_notes. Spring genera la implementación.
 */
public interface ClinicalNoteJpaRepository extends JpaRepository<ClinicalNoteJpaEntity, UUID> {
}
