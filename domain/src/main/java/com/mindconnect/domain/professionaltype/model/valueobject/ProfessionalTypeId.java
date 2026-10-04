package com.mindconnect.domain.professionaltype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ProfessionalType. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ProfessionalTypeId(UUID value) {

    public ProfessionalTypeId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ProfessionalTypeId generate() {
        return new ProfessionalTypeId(UUID.randomUUID());
    }
}
