package com.mindconnect.domain.study.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de Study. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record StudyId(UUID value) {

    public StudyId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static StudyId generate() {
        return new StudyId(UUID.randomUUID());
    }
}
