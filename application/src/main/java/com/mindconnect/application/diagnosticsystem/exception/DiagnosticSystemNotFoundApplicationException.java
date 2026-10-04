package com.mindconnect.application.diagnosticsystem.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public class DiagnosticSystemNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DiagnosticSystemNotFoundApplicationException(DiagnosticSystemId id) {
        super("DiagnosticSystem with id " + id.value() + " was not found.");
    }
}
