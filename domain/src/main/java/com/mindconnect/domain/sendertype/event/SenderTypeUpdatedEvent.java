package com.mindconnect.domain.sendertype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;

/**
 * Evento de dominio: se actualizó un registro de sender_types.
 */
public record SenderTypeUpdatedEvent(
        SenderTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
