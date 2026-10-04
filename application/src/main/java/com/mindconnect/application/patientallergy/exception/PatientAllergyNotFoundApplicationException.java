package com.mindconnect.application.patientallergy.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;

public class PatientAllergyNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PatientAllergyNotFoundApplicationException(PatientAllergyId id) {
        super("PatientAllergy with id " + id.value() + " was not found.");
    }
}
