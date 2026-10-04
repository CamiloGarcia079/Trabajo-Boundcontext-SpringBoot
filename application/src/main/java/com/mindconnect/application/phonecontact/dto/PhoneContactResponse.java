package com.mindconnect.application.phonecontact.dto;

import java.util.UUID;

import com.mindconnect.domain.phonecontact.model.aggregate.PhoneContact;

public record PhoneContactResponse(
        UUID id,
        UUID contactId,
        String phone,
        String notes
) {

    public static PhoneContactResponse fromDomain(PhoneContact aggregate) {
        return new PhoneContactResponse(
                aggregate.id().value(),
                aggregate.contactId(), aggregate.phone(), aggregate.notes());
    }
}
