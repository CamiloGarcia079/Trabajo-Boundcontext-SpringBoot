package com.mindconnect.domain.airunstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de AiRunStatus. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record AiRunStatusId(UUID value) {

    public AiRunStatusId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static AiRunStatusId generate() {
        return new AiRunStatusId(UUID.randomUUID());
    }
}
