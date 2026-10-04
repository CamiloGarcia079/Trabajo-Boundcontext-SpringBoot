package com.mindconnect.application.treatmentgoal.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoal.event.TreatmentGoalDeletedEvent;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.mindconnect.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class DeleteTreatmentGoalUseCase {

    private final TreatmentGoalRepository repository;

    public DeleteTreatmentGoalUseCase(TreatmentGoalRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalDeletedEvent execute(TreatmentGoalId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new TreatmentGoalDeletedEvent(id, LocalDateTime.now());
    }
}
