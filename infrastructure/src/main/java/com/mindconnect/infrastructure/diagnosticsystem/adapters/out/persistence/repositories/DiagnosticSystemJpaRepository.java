package com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;

/**
 * Repositorio de Spring Data para diagnostic_systems. Spring genera la implementación.
 */
public interface DiagnosticSystemJpaRepository extends JpaRepository<DiagnosticSystemJpaEntity, UUID> {
}
