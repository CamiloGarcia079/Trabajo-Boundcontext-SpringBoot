package com.mindconnect.application.encountermodality.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;

public class EncounterModalityNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EncounterModalityNotFoundApplicationException(EncounterModalityId id) {
        super("EncounterModality with id " + id.value() + " was not found.");
    }
}
