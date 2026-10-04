package com.mindconnect.domain.chatescalation.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatEscalation. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatEscalationId(UUID value) {

    public ChatEscalationId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatEscalationId generate() {
        return new ChatEscalationId(UUID.randomUUID());
    }
}
