package com.mindconnect.domain.encounter.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;

public class EncounterNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public EncounterNotFoundException(EncounterId id) {
        super("Encounter with id " + id.value() + " was not found.");
    }
}
