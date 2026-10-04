package com.mindconnect.application.clinicalrecord.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public class ClinicalRecordNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ClinicalRecordNotFoundApplicationException(ClinicalRecordId id) {
        super("ClinicalRecord with id " + id.value() + " was not found.");
    }
}
