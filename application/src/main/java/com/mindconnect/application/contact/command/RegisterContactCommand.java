package com.mindconnect.application.contact.command;

import java.util.UUID;

public record RegisterContactCommand(
        String fullName,
        String email,
        String notes,
        UUID cityId,
        UUID createdBy,
        UUID updatedBy
) {
}
