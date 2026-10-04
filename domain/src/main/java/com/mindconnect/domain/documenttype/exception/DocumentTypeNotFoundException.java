package com.mindconnect.domain.documenttype.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;

public class DocumentTypeNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public DocumentTypeNotFoundException(DocumentTypeId id) {
        super("DocumentType with id " + id.value() + " was not found.");
    }
}
