package com.mindconnect.application.documenttype.command;


public record RegisterDocumentTypeCommand(
        String code,
        String name
) {
}
