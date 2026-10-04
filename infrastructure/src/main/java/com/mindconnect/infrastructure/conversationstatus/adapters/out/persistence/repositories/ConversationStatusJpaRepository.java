package com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;

/**
 * Repositorio de Spring Data para conversations_statuses. Spring genera la implementación.
 */
public interface ConversationStatusJpaRepository extends JpaRepository<ConversationStatusJpaEntity, UUID> {
}
