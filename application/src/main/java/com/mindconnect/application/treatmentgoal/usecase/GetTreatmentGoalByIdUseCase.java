package com.mindconnect.application.treatmentgoal.usecase;

import com.mindconnect.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.mindconnect.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.mindconnect.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class GetTreatmentGoalByIdUseCase {

    private final TreatmentGoalRepository repository;

    public GetTreatmentGoalByIdUseCase(TreatmentGoalRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalResponse execute(TreatmentGoalId id) {
        return repository.findById(id)
                .map(TreatmentGoalResponse::fromDomain)
                .orElseThrow(() -> new TreatmentGoalNotFoundApplicationException(id));
    }
}
