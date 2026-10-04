package com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;

/**
 * Repositorio de Spring Data para chat_conversations. Spring genera la implementación.
 */
public interface ChatConversationJpaRepository extends JpaRepository<ChatConversationJpaEntity, UUID> {
}
