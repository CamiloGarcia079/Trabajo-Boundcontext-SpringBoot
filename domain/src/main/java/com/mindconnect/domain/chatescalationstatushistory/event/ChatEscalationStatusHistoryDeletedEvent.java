package com.mindconnect.domain.chatescalationstatushistory.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

/**
 * Evento de dominio: se eliminó un registro de chat_escalation_status_history.
 */
public record ChatEscalationStatusHistoryDeletedEvent(
        ChatEscalationStatusHistoryId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
