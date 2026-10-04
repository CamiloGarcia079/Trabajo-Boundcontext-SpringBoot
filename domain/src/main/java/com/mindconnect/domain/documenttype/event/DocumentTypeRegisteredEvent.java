package com.mindconnect.domain.documenttype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;

/**
 * Evento de dominio: se registró un registro de document_types.
 */
public record DocumentTypeRegisteredEvent(
        DocumentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
