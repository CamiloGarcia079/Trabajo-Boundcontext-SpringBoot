package com.mindconnect.application.risklevel.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;

public class RiskLevelNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public RiskLevelNotFoundApplicationException(RiskLevelId id) {
        super("RiskLevel with id " + id.value() + " was not found.");
    }
}
