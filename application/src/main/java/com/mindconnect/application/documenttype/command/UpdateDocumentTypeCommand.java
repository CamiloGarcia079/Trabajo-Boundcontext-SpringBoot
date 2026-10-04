package com.mindconnect.application.documenttype.command;


import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;

public record UpdateDocumentTypeCommand(
        DocumentTypeId id,
        String code,
        String name
) {
}
