package com.mindconnect.domain.patientallergy.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;

public class PatientAllergyNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public PatientAllergyNotFoundException(PatientAllergyId id) {
        super("PatientAllergy with id " + id.value() + " was not found.");
    }
}
