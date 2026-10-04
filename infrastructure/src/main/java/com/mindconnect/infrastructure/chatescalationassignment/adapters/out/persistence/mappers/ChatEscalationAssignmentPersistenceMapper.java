package com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.mappers;

import com.mindconnect.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de chat_escalation_assignments.
 */
public class ChatEscalationAssignmentPersistenceMapper {

    public ChatEscalationAssignmentJpaEntity toJpa(ChatEscalationAssignment domain) {
        if (domain == null) {
            return null;
        }

        ChatEscalationAssignmentJpaEntity jpa = new ChatEscalationAssignmentJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEscalationId(domain.escalationId());
        jpa.setProfessionalId(domain.professionalId());
        jpa.setAssignedAt(domain.assignedAt());

        return jpa;
    }

    public ChatEscalationAssignment toDomain(ChatEscalationAssignmentJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ChatEscalationAssignment.restore(
                new ChatEscalationAssignmentId(jpa.getId()),
                jpa.getEscalationId(), jpa.getProfessionalId(), jpa.getAssignedAt());
    }
}
