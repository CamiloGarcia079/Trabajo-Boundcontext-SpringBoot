package com.mindconnect.application.treatmentplan.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import com.mindconnect.domain.treatmentplan.event.TreatmentPlanDeletedEvent;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.mindconnect.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class DeleteTreatmentPlanUseCase {

    private final TreatmentPlanRepository repository;

    public DeleteTreatmentPlanUseCase(TreatmentPlanRepository repository) {
        this.repository = repository;
    }

    public TreatmentPlanDeletedEvent execute(TreatmentPlanId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentPlanNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new TreatmentPlanDeletedEvent(id, LocalDateTime.now());
    }
}
