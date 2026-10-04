package com.mindconnect.application.patient.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.patient.model.valueobject.PatientId;

public class PatientNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PatientNotFoundApplicationException(PatientId id) {
        super("Patient with id " + id.value() + " was not found.");
    }
}
