package com.mindconnect.application.treatmentplan.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public class TreatmentPlanNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public TreatmentPlanNotFoundApplicationException(TreatmentPlanId id) {
        super("TreatmentPlan with id " + id.value() + " was not found.");
    }
}
