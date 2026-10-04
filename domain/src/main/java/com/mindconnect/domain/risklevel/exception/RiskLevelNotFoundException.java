package com.mindconnect.domain.risklevel.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;

public class RiskLevelNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public RiskLevelNotFoundException(RiskLevelId id) {
        super("RiskLevel with id " + id.value() + " was not found.");
    }
}
