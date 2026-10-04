package com.mindconnect.domain.chatairunerror.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatAiRunError. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatAiRunErrorId(UUID value) {

    public ChatAiRunErrorId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatAiRunErrorId generate() {
        return new ChatAiRunErrorId(UUID.randomUUID());
    }
}
