package com.mindconnect.domain.chatparticipant.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatParticipant. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatParticipantId(UUID value) {

    public ChatParticipantId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatParticipantId generate() {
        return new ChatParticipantId(UUID.randomUUID());
    }
}
