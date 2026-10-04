package com.mindconnect.application.treatmentstatus.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public class TreatmentStatusNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public TreatmentStatusNotFoundApplicationException(TreatmentStatusId id) {
        super("TreatmentStatus with id " + id.value() + " was not found.");
    }
}
