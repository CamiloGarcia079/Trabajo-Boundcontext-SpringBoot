package com.mindconnect.application.encounterstatus.usecase;

import com.mindconnect.application.encounterstatus.command.UpdateEncounterStatusCommand;
import com.mindconnect.application.encounterstatus.dto.EncounterStatusResponse;
import com.mindconnect.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.mindconnect.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.mindconnect.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class UpdateEncounterStatusUseCase {

    private final EncounterStatusRepository repository;

    public UpdateEncounterStatusUseCase(EncounterStatusRepository repository) {
        this.repository = repository;
    }

    public EncounterStatusResponse execute(UpdateEncounterStatusCommand command) {
        EncounterStatus aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EncounterStatusNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name());
        EncounterStatus saved = repository.save(aggregate);
        return EncounterStatusResponse.fromDomain(saved);
    }
}
