package com.mindconnect.application.treatmentstatus.usecase;

import com.mindconnect.application.treatmentstatus.command.RegisterTreatmentStatusCommand;
import com.mindconnect.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.mindconnect.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class RegisterTreatmentStatusUseCase {

    private final TreatmentStatusRepository repository;

    public RegisterTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentStatusResponse execute(RegisterTreatmentStatusCommand command) {
        TreatmentStatus aggregate = TreatmentStatus.register(
                command.code(), command.name());
        TreatmentStatus saved = repository.save(aggregate);
        return TreatmentStatusResponse.fromDomain(saved);
    }
}
