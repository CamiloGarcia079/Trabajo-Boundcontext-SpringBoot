package com.mindconnect.domain.chatparticipant.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;

/**
 * Evento de dominio: se actualizó un registro de chat_participants.
 */
public record ChatParticipantUpdatedEvent(
        ChatParticipantId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
