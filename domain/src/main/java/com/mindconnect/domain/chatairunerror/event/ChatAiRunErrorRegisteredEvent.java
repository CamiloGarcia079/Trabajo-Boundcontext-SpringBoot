package com.mindconnect.domain.chatairunerror.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

/**
 * Evento de dominio: se registró un registro de chat_ai_run_errors.
 */
public record ChatAiRunErrorRegisteredEvent(
        ChatAiRunErrorId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
