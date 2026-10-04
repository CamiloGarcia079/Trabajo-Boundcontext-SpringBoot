package com.mindconnect.infrastructure.chatairunmetric.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para crear un registro de chat_ai_run_metrics.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateChatAiRunMetricRequest(
        @NotNull UUID aiRunId,
        @NotNull Integer promptTokens,
        @NotNull Integer completionTokens,
        @NotNull Integer totalTokens,
        @NotNull BigDecimal cost
) {
}
