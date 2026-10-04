package com.mindconnect.domain.patient.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.patient.model.valueobject.PatientId;

public class PatientNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public PatientNotFoundException(PatientId id) {
        super("Patient with id " + id.value() + " was not found.");
    }
}
