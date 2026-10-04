package com.mindconnect.application.escalationstatus.usecase;

import com.mindconnect.application.escalationstatus.command.UpdateEscalationStatusCommand;
import com.mindconnect.application.escalationstatus.dto.EscalationStatusResponse;
import com.mindconnect.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.mindconnect.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.mindconnect.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class UpdateEscalationStatusUseCase {

    private final EscalationStatusRepository repository;

    public UpdateEscalationStatusUseCase(EscalationStatusRepository repository) {
        this.repository = repository;
    }

    public EscalationStatusResponse execute(UpdateEscalationStatusCommand command) {
        EscalationStatus aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EscalationStatusNotFoundApplicationException(command.id()));
        aggregate.update(
                command.nameStatus());
        EscalationStatus saved = repository.save(aggregate);
        return EscalationStatusResponse.fromDomain(saved);
    }
}
