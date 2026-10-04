package com.mindconnect.domain.aimodel.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de AiModel. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record AiModelId(UUID value) {

    public AiModelId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static AiModelId generate() {
        return new AiModelId(UUID.randomUUID());
    }
}
