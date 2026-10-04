package com.mindconnect.domain.aimodel.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;

/**
 * Evento de dominio: se registró un registro de ai_models.
 */
public record AiModelRegisteredEvent(
        AiModelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
