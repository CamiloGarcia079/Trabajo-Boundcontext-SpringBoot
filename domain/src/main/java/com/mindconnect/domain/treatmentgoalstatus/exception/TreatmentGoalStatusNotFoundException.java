package com.mindconnect.domain.treatmentgoalstatus.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public class TreatmentGoalStatusNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public TreatmentGoalStatusNotFoundException(TreatmentGoalStatusId id) {
        super("TreatmentGoalStatus with id " + id.value() + " was not found.");
    }
}
