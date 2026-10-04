package com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;

/**
 * Repositorio de Spring Data para chat_messages. Spring genera la implementación.
 */
public interface ChatMessageJpaRepository extends JpaRepository<ChatMessageJpaEntity, UUID> {
}
