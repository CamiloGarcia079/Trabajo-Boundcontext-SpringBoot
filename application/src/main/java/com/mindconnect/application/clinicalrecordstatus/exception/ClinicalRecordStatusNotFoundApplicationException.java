package com.mindconnect.application.clinicalrecordstatus.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public class ClinicalRecordStatusNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ClinicalRecordStatusNotFoundApplicationException(ClinicalRecordStatusId id) {
        super("ClinicalRecordStatus with id " + id.value() + " was not found.");
    }
}
