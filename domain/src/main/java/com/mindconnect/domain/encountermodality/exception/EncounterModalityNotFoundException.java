package com.mindconnect.domain.encountermodality.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;

public class EncounterModalityNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public EncounterModalityNotFoundException(EncounterModalityId id) {
        super("EncounterModality with id " + id.value() + " was not found.");
    }
}
