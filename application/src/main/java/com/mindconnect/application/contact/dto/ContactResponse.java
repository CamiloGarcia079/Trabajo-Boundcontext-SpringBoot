package com.mindconnect.application.contact.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.contact.model.aggregate.Contact;

public record ContactResponse(
        UUID id,
        String fullName,
        String email,
        String notes,
        UUID cityId,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy
) {

    public static ContactResponse fromDomain(Contact aggregate) {
        return new ContactResponse(
                aggregate.id().value(),
                aggregate.fullName(), aggregate.email(), aggregate.notes(), aggregate.cityId(), aggregate.createdAt(), aggregate.createdBy(), aggregate.updatedAt(), aggregate.updatedBy());
    }
}
