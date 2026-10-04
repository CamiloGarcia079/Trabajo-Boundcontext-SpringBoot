package com.mindconnect.domain.chatescalationassignment.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

/**
 * Evento de dominio: se actualizó un registro de chat_escalation_assignments.
 */
public record ChatEscalationAssignmentUpdatedEvent(
        ChatEscalationAssignmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
