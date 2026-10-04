package com.mindconnect.domain.stateregion.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;

public class StateRegionNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public StateRegionNotFoundException(StateRegionId id) {
        super("StateRegion with id " + id.value() + " was not found.");
    }
}
