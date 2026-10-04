package com.mindconnect.domain.risklevel.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de RiskLevel. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record RiskLevelId(UUID value) {

    public RiskLevelId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static RiskLevelId generate() {
        return new RiskLevelId(UUID.randomUUID());
    }
}
