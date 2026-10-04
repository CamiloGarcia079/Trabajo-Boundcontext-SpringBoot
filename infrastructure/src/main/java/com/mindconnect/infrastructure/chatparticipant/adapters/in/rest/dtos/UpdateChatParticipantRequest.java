package com.mindconnect.infrastructure.chatparticipant.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de chat_participants.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateChatParticipantRequest(
        @NotNull UUID conversationId,
        @NotNull UUID participantTypeId,
        UUID patientId,
        UUID professionalId
) {
}
