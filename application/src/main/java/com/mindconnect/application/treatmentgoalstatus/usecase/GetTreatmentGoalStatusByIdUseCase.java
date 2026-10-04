package com.mindconnect.application.treatmentgoalstatus.usecase;

import com.mindconnect.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.mindconnect.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.mindconnect.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class GetTreatmentGoalStatusByIdUseCase {

    private final TreatmentGoalStatusRepository repository;

    public GetTreatmentGoalStatusByIdUseCase(TreatmentGoalStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalStatusResponse execute(TreatmentGoalStatusId id) {
        return repository.findById(id)
                .map(TreatmentGoalStatusResponse::fromDomain)
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundApplicationException(id));
    }
}
