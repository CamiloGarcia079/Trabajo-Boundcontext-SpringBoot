package com.mindconnect.domain.chatescalationstatushistory.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatEscalationStatusHistory. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatEscalationStatusHistoryId(UUID value) {

    public ChatEscalationStatusHistoryId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatEscalationStatusHistoryId generate() {
        return new ChatEscalationStatusHistoryId(UUID.randomUUID());
    }
}
