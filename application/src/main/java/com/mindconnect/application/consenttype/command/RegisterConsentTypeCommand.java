package com.mindconnect.application.consenttype.command;


public record RegisterConsentTypeCommand(
        String code,
        String name,
        String description
) {
}
