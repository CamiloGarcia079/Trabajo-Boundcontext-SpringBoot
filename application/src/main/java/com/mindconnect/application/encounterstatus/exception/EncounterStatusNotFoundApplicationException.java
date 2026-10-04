package com.mindconnect.application.encounterstatus.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;

public class EncounterStatusNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EncounterStatusNotFoundApplicationException(EncounterStatusId id) {
        super("EncounterStatus with id " + id.value() + " was not found.");
    }
}
