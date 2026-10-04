package com.mindconnect.domain.chatairun.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatAiRun. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatAiRunId(UUID value) {

    public ChatAiRunId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatAiRunId generate() {
        return new ChatAiRunId(UUID.randomUUID());
    }
}
