package com.mindconnect.application.treatmentstatus.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentstatus.event.TreatmentStatusDeletedEvent;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.mindconnect.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class DeleteTreatmentStatusUseCase {

    private final TreatmentStatusRepository repository;

    public DeleteTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentStatusDeletedEvent execute(TreatmentStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentStatusNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new TreatmentStatusDeletedEvent(id, LocalDateTime.now());
    }
}
