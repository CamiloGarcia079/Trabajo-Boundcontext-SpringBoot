package com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.mappers;

import com.mindconnect.domain.chatconversation.model.aggregate.ChatConversation;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;
import com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de chat_conversations.
 */
public class ChatConversationPersistenceMapper {

    public ChatConversationJpaEntity toJpa(ChatConversation domain) {
        if (domain == null) {
            return null;
        }

        ChatConversationJpaEntity jpa = new ChatConversationJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationStatusId(domain.conversationStatusId());
        jpa.setPriorityId(domain.priorityId());
        jpa.setLastMessageAt(domain.lastMessageAt());
        jpa.setClosed(domain.closed());
        jpa.setClosedAt(domain.closedAt());
        jpa.setClosedBy(domain.closedBy());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ChatConversation toDomain(ChatConversationJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ChatConversation.restore(
                new ChatConversationId(jpa.getId()),
                jpa.getConversationStatusId(), jpa.getPriorityId(), jpa.getLastMessageAt(), jpa.getClosed(), jpa.getClosedAt(), jpa.getClosedBy(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
