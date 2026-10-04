package com.mindconnect.application.phonecontact.command;

import java.util.UUID;

import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;

public record UpdatePhoneContactCommand(
        PhoneContactId id,
        UUID contactId,
        String phone,
        String notes
) {
}
