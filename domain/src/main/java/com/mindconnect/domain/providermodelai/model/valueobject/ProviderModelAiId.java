package com.mindconnect.domain.providermodelai.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ProviderModelAi. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ProviderModelAiId(UUID value) {

    public ProviderModelAiId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ProviderModelAiId generate() {
        return new ProviderModelAiId(UUID.randomUUID());
    }
}
