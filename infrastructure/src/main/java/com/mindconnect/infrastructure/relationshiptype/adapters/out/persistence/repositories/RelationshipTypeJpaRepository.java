package com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;

/**
 * Repositorio de Spring Data para relationship_types. Spring genera la implementación.
 */
public interface RelationshipTypeJpaRepository extends JpaRepository<RelationshipTypeJpaEntity, UUID> {
}
