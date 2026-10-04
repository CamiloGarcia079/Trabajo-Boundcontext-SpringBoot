package com.mindconnect.domain.messagetype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de MessageType. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record MessageTypeId(UUID value) {

    public MessageTypeId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static MessageTypeId generate() {
        return new MessageTypeId(UUID.randomUUID());
    }
}
