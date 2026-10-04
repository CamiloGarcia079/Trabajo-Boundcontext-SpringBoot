package com.mindconnect.domain.chatairun.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatairun.model.valueobject.ChatAiRunId;

/**
 * Evento de dominio: se registró un registro de chat_ai_runs.
 */
public record ChatAiRunRegisteredEvent(
        ChatAiRunId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
