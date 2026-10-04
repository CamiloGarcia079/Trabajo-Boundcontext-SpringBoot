package com.mindconnect.domain.chatmessage.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatMessage. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatMessageId(UUID value) {

    public ChatMessageId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatMessageId generate() {
        return new ChatMessageId(UUID.randomUUID());
    }
}
