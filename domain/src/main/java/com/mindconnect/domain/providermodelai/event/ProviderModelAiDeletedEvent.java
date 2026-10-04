package com.mindconnect.domain.providermodelai.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;

/**
 * Evento de dominio: se eliminó un registro de provider_models_ai.
 */
public record ProviderModelAiDeletedEvent(
        ProviderModelAiId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
