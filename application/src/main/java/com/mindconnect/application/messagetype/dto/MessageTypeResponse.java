package com.mindconnect.application.messagetype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.messagetype.model.aggregate.MessageType;

public record MessageTypeResponse(
        UUID id,
        String nameType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static MessageTypeResponse fromDomain(MessageType aggregate) {
        return new MessageTypeResponse(
                aggregate.id().value(),
                aggregate.nameType(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
