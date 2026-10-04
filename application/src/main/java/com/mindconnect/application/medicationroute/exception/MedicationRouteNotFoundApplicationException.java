package com.mindconnect.application.medicationroute.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;

public class MedicationRouteNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public MedicationRouteNotFoundApplicationException(MedicationRouteId id) {
        super("MedicationRoute with id " + id.value() + " was not found.");
    }
}
