package com.mindconnect.application.patientcontact.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;

public class PatientContactNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PatientContactNotFoundApplicationException(PatientContactId id) {
        super("PatientContact with id " + id.value() + " was not found.");
    }
}
