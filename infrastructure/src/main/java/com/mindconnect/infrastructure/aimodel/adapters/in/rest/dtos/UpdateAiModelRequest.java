package com.mindconnect.infrastructure.aimodel.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de ai_models.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateAiModelRequest(
        @NotNull UUID providerModelId,
        @NotBlank @Size(max = 100) String nameModel,
        @NotBlank @Size(max = 120) String modelKey,
        @NotNull BigDecimal inputTokenPrice,
        @NotNull BigDecimal outputTokenPrice,
        @NotNull Integer maxTokens,
        @NotNull Integer contextWindow
) {
}
