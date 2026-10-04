package com.mindconnect.domain.professionalstudy.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ProfessionalStudy. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ProfessionalStudyId(UUID value) {

    public ProfessionalStudyId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ProfessionalStudyId generate() {
        return new ProfessionalStudyId(UUID.randomUUID());
    }
}
