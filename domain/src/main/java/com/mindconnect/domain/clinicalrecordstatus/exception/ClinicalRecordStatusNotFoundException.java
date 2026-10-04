package com.mindconnect.domain.clinicalrecordstatus.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public class ClinicalRecordStatusNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ClinicalRecordStatusNotFoundException(ClinicalRecordStatusId id) {
        super("ClinicalRecordStatus with id " + id.value() + " was not found.");
    }
}
