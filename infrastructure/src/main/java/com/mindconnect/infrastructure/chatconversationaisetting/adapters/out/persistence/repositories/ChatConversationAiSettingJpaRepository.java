package com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;

/**
 * Repositorio de Spring Data para chat_conversation_ai_settings. Spring genera la implementación.
 */
public interface ChatConversationAiSettingJpaRepository extends JpaRepository<ChatConversationAiSettingJpaEntity, UUID> {
}
