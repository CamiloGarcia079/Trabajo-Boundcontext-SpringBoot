package com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;

/**
 * Repositorio de Spring Data para patient_allergies. Spring genera la implementación.
 */
public interface PatientAllergyJpaRepository extends JpaRepository<PatientAllergyJpaEntity, UUID> {
}
