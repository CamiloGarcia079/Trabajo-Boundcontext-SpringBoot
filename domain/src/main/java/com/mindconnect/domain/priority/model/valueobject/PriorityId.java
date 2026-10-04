package com.mindconnect.domain.priority.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de Priority. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record PriorityId(UUID value) {

    public PriorityId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static PriorityId generate() {
        return new PriorityId(UUID.randomUUID());
    }
}
