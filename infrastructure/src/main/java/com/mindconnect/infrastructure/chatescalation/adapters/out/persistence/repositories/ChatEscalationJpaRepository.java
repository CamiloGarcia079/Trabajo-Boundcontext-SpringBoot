package com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;

/**
 * Repositorio de Spring Data para chat_escalations. Spring genera la implementación.
 */
public interface ChatEscalationJpaRepository extends JpaRepository<ChatEscalationJpaEntity, UUID> {
}
