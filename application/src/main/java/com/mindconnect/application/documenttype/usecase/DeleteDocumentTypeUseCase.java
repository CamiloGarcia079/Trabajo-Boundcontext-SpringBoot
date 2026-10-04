package com.mindconnect.application.documenttype.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.mindconnect.domain.documenttype.event.DocumentTypeDeletedEvent;
import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;
import com.mindconnect.domain.documenttype.port.repository.DocumentTypeRepository;

public class DeleteDocumentTypeUseCase {

    private final DocumentTypeRepository repository;

    public DeleteDocumentTypeUseCase(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    public DocumentTypeDeletedEvent execute(DocumentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DocumentTypeNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new DocumentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
