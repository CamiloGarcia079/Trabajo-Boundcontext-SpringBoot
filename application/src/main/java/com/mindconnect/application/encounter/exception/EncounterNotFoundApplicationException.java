package com.mindconnect.application.encounter.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;

public class EncounterNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EncounterNotFoundApplicationException(EncounterId id) {
        super("Encounter with id " + id.value() + " was not found.");
    }
}
