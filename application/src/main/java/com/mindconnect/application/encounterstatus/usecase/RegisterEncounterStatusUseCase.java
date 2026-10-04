package com.mindconnect.application.encounterstatus.usecase;

import com.mindconnect.application.encounterstatus.command.RegisterEncounterStatusCommand;
import com.mindconnect.application.encounterstatus.dto.EncounterStatusResponse;
import com.mindconnect.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.mindconnect.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class RegisterEncounterStatusUseCase {

    private final EncounterStatusRepository repository;

    public RegisterEncounterStatusUseCase(EncounterStatusRepository repository) {
        this.repository = repository;
    }

    public EncounterStatusResponse execute(RegisterEncounterStatusCommand command) {
        EncounterStatus aggregate = EncounterStatus.register(
                command.code(), command.name());
        EncounterStatus saved = repository.save(aggregate);
        return EncounterStatusResponse.fromDomain(saved);
    }
}
