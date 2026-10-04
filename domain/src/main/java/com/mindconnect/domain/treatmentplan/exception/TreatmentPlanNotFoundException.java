package com.mindconnect.domain.treatmentplan.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public class TreatmentPlanNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public TreatmentPlanNotFoundException(TreatmentPlanId id) {
        super("TreatmentPlan with id " + id.value() + " was not found.");
    }
}
