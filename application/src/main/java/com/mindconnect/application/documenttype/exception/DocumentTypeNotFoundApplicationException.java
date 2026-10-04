package com.mindconnect.application.documenttype.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;

public class DocumentTypeNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DocumentTypeNotFoundApplicationException(DocumentTypeId id) {
        super("DocumentType with id " + id.value() + " was not found.");
    }
}
