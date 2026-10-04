package com.mindconnect.domain.diagnosticsystem.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public class DiagnosticSystemNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public DiagnosticSystemNotFoundException(DiagnosticSystemId id) {
        super("DiagnosticSystem with id " + id.value() + " was not found.");
    }
}
