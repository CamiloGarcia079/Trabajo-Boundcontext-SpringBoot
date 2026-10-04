package com.mindconnect.domain.medicationroute.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;

public class MedicationRouteNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public MedicationRouteNotFoundException(MedicationRouteId id) {
        super("MedicationRoute with id " + id.value() + " was not found.");
    }
}
