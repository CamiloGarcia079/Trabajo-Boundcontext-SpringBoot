package com.mindconnect.application.treatmentgoalstatus.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public class TreatmentGoalStatusNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public TreatmentGoalStatusNotFoundApplicationException(TreatmentGoalStatusId id) {
        super("TreatmentGoalStatus with id " + id.value() + " was not found.");
    }
}
