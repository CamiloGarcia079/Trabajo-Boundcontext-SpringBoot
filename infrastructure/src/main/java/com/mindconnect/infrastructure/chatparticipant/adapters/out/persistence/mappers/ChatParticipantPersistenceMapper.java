package com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.mappers;

import com.mindconnect.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de chat_participants.
 */
public class ChatParticipantPersistenceMapper {

    public ChatParticipantJpaEntity toJpa(ChatParticipant domain) {
        if (domain == null) {
            return null;
        }

        ChatParticipantJpaEntity jpa = new ChatParticipantJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId());
        jpa.setParticipantTypeId(domain.participantTypeId());
        jpa.setPatientId(domain.patientId());
        jpa.setProfessionalId(domain.professionalId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ChatParticipant toDomain(ChatParticipantJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ChatParticipant.restore(
                new ChatParticipantId(jpa.getId()),
                jpa.getConversationId(), jpa.getParticipantTypeId(), jpa.getPatientId(), jpa.getProfessionalId(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
