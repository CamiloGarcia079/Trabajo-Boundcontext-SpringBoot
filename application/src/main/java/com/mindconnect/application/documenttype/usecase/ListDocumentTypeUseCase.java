package com.mindconnect.application.documenttype.usecase;

import java.util.List;

import com.mindconnect.application.documenttype.dto.DocumentTypeResponse;
import com.mindconnect.domain.documenttype.port.repository.DocumentTypeRepository;

public class ListDocumentTypeUseCase {

    private final DocumentTypeRepository repository;

    public ListDocumentTypeUseCase(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    public List<DocumentTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(DocumentTypeResponse::fromDomain)
                .toList();
    }
}
