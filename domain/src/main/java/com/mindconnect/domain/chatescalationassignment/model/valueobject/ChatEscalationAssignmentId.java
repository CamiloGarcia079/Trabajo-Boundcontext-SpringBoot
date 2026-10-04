package com.mindconnect.domain.chatescalationassignment.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatEscalationAssignment. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatEscalationAssignmentId(UUID value) {

    public ChatEscalationAssignmentId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatEscalationAssignmentId generate() {
        return new ChatEscalationAssignmentId(UUID.randomUUID());
    }
}
