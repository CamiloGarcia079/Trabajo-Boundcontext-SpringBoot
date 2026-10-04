package com.mindconnect.domain.aimodel.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;

/**
 * Evento de dominio: se actualizó un registro de ai_models.
 */
public record AiModelUpdatedEvent(
        AiModelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
