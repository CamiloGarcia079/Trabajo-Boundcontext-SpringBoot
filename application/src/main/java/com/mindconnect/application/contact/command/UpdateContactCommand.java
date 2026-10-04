package com.mindconnect.application.contact.command;

import java.util.UUID;

import com.mindconnect.domain.contact.model.valueobject.ContactId;

public record UpdateContactCommand(
        ContactId id,
        String fullName,
        String email,
        String notes,
        UUID cityId,
        UUID updatedBy
) {
}
