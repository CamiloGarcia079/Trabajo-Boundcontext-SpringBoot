package com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;

/**
 * Repositorio de Spring Data para clinical_records. Spring genera la implementación.
 */
public interface ClinicalRecordJpaRepository extends JpaRepository<ClinicalRecordJpaEntity, UUID> {
}
