package com.mindconnect.domain.treatmentgoal.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

public class TreatmentGoalNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public TreatmentGoalNotFoundException(TreatmentGoalId id) {
        super("TreatmentGoal with id " + id.value() + " was not found.");
    }
}
