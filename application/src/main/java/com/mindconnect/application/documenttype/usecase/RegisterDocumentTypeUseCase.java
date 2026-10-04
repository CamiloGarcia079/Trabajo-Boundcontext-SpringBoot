package com.mindconnect.application.documenttype.usecase;

import com.mindconnect.application.documenttype.command.RegisterDocumentTypeCommand;
import com.mindconnect.application.documenttype.dto.DocumentTypeResponse;
import com.mindconnect.domain.documenttype.model.aggregate.DocumentType;
import com.mindconnect.domain.documenttype.port.repository.DocumentTypeRepository;

public class RegisterDocumentTypeUseCase {

    private final DocumentTypeRepository repository;

    public RegisterDocumentTypeUseCase(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    public DocumentTypeResponse execute(RegisterDocumentTypeCommand command) {
        DocumentType aggregate = DocumentType.register(
                command.code(), command.name());
        DocumentType saved = repository.save(aggregate);
        return DocumentTypeResponse.fromDomain(saved);
    }
}
