package com.mindconnect.application.documenttype.usecase;

import com.mindconnect.application.documenttype.dto.DocumentTypeResponse;
import com.mindconnect.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;
import com.mindconnect.domain.documenttype.port.repository.DocumentTypeRepository;

public class GetDocumentTypeByIdUseCase {

    private final DocumentTypeRepository repository;

    public GetDocumentTypeByIdUseCase(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    public DocumentTypeResponse execute(DocumentTypeId id) {
        return repository.findById(id)
                .map(DocumentTypeResponse::fromDomain)
                .orElseThrow(() -> new DocumentTypeNotFoundApplicationException(id));
    }
}
