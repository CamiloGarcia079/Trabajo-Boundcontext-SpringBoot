package com.mindconnect.application.chatparticipant.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatparticipant.model.aggregate.ChatParticipant;

public record ChatParticipantResponse(
        UUID id,
        UUID conversationId,
        UUID participantTypeId,
        UUID patientId,
        UUID professionalId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ChatParticipantResponse fromDomain(ChatParticipant aggregate) {
        return new ChatParticipantResponse(
                aggregate.id().value(),
                aggregate.conversationId(), aggregate.participantTypeId(), aggregate.patientId(), aggregate.professionalId(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
