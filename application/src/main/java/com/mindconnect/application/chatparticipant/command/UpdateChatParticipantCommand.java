package com.mindconnect.application.chatparticipant.command;

import java.util.UUID;

import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;

public record UpdateChatParticipantCommand(
        ChatParticipantId id,
        UUID conversationId,
        UUID participantTypeId,
        UUID patientId,
        UUID professionalId
) {
}
