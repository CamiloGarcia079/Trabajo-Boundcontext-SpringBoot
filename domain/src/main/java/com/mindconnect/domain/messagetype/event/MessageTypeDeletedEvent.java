package com.mindconnect.domain.messagetype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;

/**
 * Evento de dominio: se eliminó un registro de message_types.
 */
public record MessageTypeDeletedEvent(
        MessageTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
