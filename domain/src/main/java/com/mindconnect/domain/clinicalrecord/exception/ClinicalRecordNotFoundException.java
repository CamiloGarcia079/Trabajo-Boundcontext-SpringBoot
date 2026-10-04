package com.mindconnect.domain.clinicalrecord.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public class ClinicalRecordNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ClinicalRecordNotFoundException(ClinicalRecordId id) {
        super("ClinicalRecord with id " + id.value() + " was not found.");
    }
}
