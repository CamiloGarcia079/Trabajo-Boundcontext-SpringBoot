package com.mindconnect.application.consenttype.command;


import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;

public record UpdateConsentTypeCommand(
        ConsentTypeId id,
        String code,
        String name,
        String description
) {
}
