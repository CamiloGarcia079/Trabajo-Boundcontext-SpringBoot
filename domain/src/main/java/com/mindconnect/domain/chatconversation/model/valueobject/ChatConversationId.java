package com.mindconnect.domain.chatconversation.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatConversation. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatConversationId(UUID value) {

    public ChatConversationId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatConversationId generate() {
        return new ChatConversationId(UUID.randomUUID());
    }
}
