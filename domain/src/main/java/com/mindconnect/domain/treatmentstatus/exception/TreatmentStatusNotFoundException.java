package com.mindconnect.domain.treatmentstatus.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public class TreatmentStatusNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public TreatmentStatusNotFoundException(TreatmentStatusId id) {
        super("TreatmentStatus with id " + id.value() + " was not found.");
    }
}
