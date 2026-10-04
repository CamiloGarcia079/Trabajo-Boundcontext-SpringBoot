package com.mindconnect.domain.providermodelai.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;

/**
 * Evento de dominio: se actualizó un registro de provider_models_ai.
 */
public record ProviderModelAiUpdatedEvent(
        ProviderModelAiId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
