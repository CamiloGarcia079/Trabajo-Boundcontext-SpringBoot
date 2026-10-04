package com.mindconnect.domain.conversationstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ConversationStatus. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ConversationStatusId(UUID value) {

    public ConversationStatusId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ConversationStatusId generate() {
        return new ConversationStatusId(UUID.randomUUID());
    }
}
