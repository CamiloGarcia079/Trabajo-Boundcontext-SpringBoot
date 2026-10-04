package com.mindconnect.application.encountertype.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;

public class EncounterTypeNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EncounterTypeNotFoundApplicationException(EncounterTypeId id) {
        super("EncounterType with id " + id.value() + " was not found.");
    }
}
