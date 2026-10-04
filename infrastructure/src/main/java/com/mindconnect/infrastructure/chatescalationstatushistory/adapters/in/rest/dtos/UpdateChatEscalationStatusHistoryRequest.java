package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de chat_escalation_status_history.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateChatEscalationStatusHistoryRequest(
        @NotNull UUID escalationId,
        @NotNull UUID escalationStatusId,
        @NotNull LocalDateTime changedAt
) {
}
