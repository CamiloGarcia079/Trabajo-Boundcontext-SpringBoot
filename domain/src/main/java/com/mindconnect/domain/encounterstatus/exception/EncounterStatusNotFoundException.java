package com.mindconnect.domain.encounterstatus.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;

public class EncounterStatusNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public EncounterStatusNotFoundException(EncounterStatusId id) {
        super("EncounterStatus with id " + id.value() + " was not found.");
    }
}
