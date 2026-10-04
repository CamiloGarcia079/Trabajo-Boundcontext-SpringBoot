package com.mindconnect.application.treatmentgoal.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

public class TreatmentGoalNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public TreatmentGoalNotFoundApplicationException(TreatmentGoalId id) {
        super("TreatmentGoal with id " + id.value() + " was not found.");
    }
}
