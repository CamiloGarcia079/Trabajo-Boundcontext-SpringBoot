package com.mindconnect.domain.encountertype.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;

public class EncounterTypeNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public EncounterTypeNotFoundException(EncounterTypeId id) {
        super("EncounterType with id " + id.value() + " was not found.");
    }
}
