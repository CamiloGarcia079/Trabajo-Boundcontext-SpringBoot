package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers;

import com.mindconnect.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de chat_escalation_status_history.
 */
public class ChatEscalationStatusHistoryPersistenceMapper {

    public ChatEscalationStatusHistoryJpaEntity toJpa(ChatEscalationStatusHistory domain) {
        if (domain == null) {
            return null;
        }

        ChatEscalationStatusHistoryJpaEntity jpa = new ChatEscalationStatusHistoryJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEscalationId(domain.escalationId());
        jpa.setEscalationStatusId(domain.escalationStatusId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setChangedAt(domain.changedAt());

        return jpa;
    }

    public ChatEscalationStatusHistory toDomain(ChatEscalationStatusHistoryJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ChatEscalationStatusHistory.restore(
                new ChatEscalationStatusHistoryId(jpa.getId()),
                jpa.getEscalationId(), jpa.getEscalationStatusId(), jpa.getCreatedAt(), jpa.getChangedAt());
    }
}
