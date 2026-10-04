package com.mindconnect.application.treatmentgoalstatus.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoalstatus.event.TreatmentGoalStatusDeletedEvent;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.mindconnect.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class DeleteTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository repository;

    public DeleteTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalStatusDeletedEvent execute(TreatmentGoalStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new TreatmentGoalStatusDeletedEvent(id, LocalDateTime.now());
    }
}
