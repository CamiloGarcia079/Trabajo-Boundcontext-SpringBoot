package com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;

/**
 * Repositorio de Spring Data para patient_contacts. Spring genera la implementación.
 */
public interface PatientContactJpaRepository extends JpaRepository<PatientContactJpaEntity, UUID> {
}
