package com.mindconnect.domain.relationshiptype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;

/**
 * Evento de dominio: se registró un registro de relationship_types.
 */
public record RelationshipTypeRegisteredEvent(
        RelationshipTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
