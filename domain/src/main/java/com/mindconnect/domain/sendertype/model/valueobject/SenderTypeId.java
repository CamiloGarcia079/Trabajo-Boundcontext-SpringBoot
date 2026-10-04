package com.mindconnect.domain.sendertype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de SenderType. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record SenderTypeId(UUID value) {

    public SenderTypeId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static SenderTypeId generate() {
        return new SenderTypeId(UUID.randomUUID());
    }
}
