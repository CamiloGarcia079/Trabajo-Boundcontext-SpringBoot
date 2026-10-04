package com.mindconnect.domain.chatairunmetric.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ChatAiRunMetric. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ChatAiRunMetricId(UUID value) {

    public ChatAiRunMetricId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ChatAiRunMetricId generate() {
        return new ChatAiRunMetricId(UUID.randomUUID());
    }
}
