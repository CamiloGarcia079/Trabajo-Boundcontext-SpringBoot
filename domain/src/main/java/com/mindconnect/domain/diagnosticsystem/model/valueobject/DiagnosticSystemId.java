package com.mindconnect.domain.diagnosticsystem.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de DiagnosticSystem. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record DiagnosticSystemId(UUID value) {

    public DiagnosticSystemId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static DiagnosticSystemId generate() {
        return new DiagnosticSystemId(UUID.randomUUID());
    }
}
