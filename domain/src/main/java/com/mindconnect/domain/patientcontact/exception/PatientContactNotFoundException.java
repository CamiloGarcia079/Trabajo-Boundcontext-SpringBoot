package com.mindconnect.domain.patientcontact.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;

public class PatientContactNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public PatientContactNotFoundException(PatientContactId id) {
        super("PatientContact with id " + id.value() + " was not found.");
    }
}
