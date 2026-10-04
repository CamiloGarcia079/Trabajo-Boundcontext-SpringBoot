package com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers;

import com.mindconnect.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de chat_conversation_ai_settings.
 */
public class ChatConversationAiSettingPersistenceMapper {

    public ChatConversationAiSettingJpaEntity toJpa(ChatConversationAiSetting domain) {
        if (domain == null) {
            return null;
        }

        ChatConversationAiSettingJpaEntity jpa = new ChatConversationAiSettingJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId());
        jpa.setAiEnabled(domain.aiEnabled());
        jpa.setDefaultModelId(domain.defaultModelId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ChatConversationAiSetting toDomain(ChatConversationAiSettingJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ChatConversationAiSetting.restore(
                new ChatConversationAiSettingId(jpa.getId()),
                jpa.getConversationId(), jpa.getAiEnabled(), jpa.getDefaultModelId(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
