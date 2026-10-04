package com.mindconnect.application.emailcontact.command;

import java.util.UUID;

import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;

public record UpdateEmailContactCommand(
        EmailContactId id,
        UUID contactId,
        String email,
        String notes
) {
}
