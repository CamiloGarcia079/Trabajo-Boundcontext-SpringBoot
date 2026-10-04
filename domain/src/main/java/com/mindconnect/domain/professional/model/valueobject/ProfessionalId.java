package com.mindconnect.domain.professional.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de Professional. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ProfessionalId(UUID value) {

    public ProfessionalId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ProfessionalId generate() {
        return new ProfessionalId(UUID.randomUUID());
    }
}
