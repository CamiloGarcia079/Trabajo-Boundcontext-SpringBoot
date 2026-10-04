package com.mindconnect.application.treatmentstatus.usecase;

import com.mindconnect.application.treatmentstatus.command.UpdateTreatmentStatusCommand;
import com.mindconnect.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.mindconnect.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class UpdateTreatmentStatusUseCase {

    private final TreatmentStatusRepository repository;

    public UpdateTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentStatusResponse execute(UpdateTreatmentStatusCommand command) {
        TreatmentStatus aggregate = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentStatusNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name());
        TreatmentStatus saved = repository.save(aggregate);
        return TreatmentStatusResponse.fromDomain(saved);
    }
}
