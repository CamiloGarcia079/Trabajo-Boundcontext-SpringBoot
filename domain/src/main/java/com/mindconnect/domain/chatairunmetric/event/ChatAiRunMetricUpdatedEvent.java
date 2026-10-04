package com.mindconnect.domain.chatairunmetric.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

/**
 * Evento de dominio: se actualizó un registro de chat_ai_run_metrics.
 */
public record ChatAiRunMetricUpdatedEvent(
        ChatAiRunMetricId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
