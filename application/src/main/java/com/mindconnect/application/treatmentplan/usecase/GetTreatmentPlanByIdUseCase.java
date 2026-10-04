package com.mindconnect.application.treatmentplan.usecase;

import com.mindconnect.application.treatmentplan.dto.TreatmentPlanResponse;
import com.mindconnect.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.mindconnect.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class GetTreatmentPlanByIdUseCase {

    private final TreatmentPlanRepository repository;

    public GetTreatmentPlanByIdUseCase(TreatmentPlanRepository repository) {
        this.repository = repository;
    }

    public TreatmentPlanResponse execute(TreatmentPlanId id) {
        return repository.findById(id)
                .map(TreatmentPlanResponse::fromDomain)
                .orElseThrow(() -> new TreatmentPlanNotFoundApplicationException(id));
    }
}
