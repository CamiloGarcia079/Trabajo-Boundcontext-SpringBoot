package com.mindconnect.domain.mentalstatusexam.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de MentalStatusExam. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record MentalStatusExamId(UUID value) {

    public MentalStatusExamId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static MentalStatusExamId generate() {
        return new MentalStatusExamId(UUID.randomUUID());
    }
}
