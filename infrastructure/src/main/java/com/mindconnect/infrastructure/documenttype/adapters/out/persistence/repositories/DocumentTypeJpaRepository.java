package com.mindconnect.infrastructure.documenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;

/**
 * Repositorio de Spring Data para document_types. Spring genera la implementación.
 */
public interface DocumentTypeJpaRepository extends JpaRepository<DocumentTypeJpaEntity, UUID> {
}
