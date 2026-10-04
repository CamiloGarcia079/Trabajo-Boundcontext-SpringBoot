package com.mindconnect.infrastructure.patient.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;

/**
 * Repositorio de Spring Data para patients. Spring genera la implementación.
 */
public interface PatientJpaRepository extends JpaRepository<PatientJpaEntity, UUID> {
}
