package com.mindconnect.domain.stateregion.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de StateRegion. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record StateRegionId(UUID value) {

    public StateRegionId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static StateRegionId generate() {
        return new StateRegionId(UUID.randomUUID());
    }
}
