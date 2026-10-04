package com.mindconnect.application.stateregion.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;

public class StateRegionNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public StateRegionNotFoundApplicationException(StateRegionId id) {
        super("StateRegion with id " + id.value() + " was not found.");
    }
}
