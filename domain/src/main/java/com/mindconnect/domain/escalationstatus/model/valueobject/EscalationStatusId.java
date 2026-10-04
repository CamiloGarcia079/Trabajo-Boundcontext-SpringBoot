package com.mindconnect.domain.escalationstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de EscalationStatus. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record EscalationStatusId(UUID value) {

    public EscalationStatusId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static EscalationStatusId generate() {
        return new EscalationStatusId(UUID.randomUUID());
    }
}
