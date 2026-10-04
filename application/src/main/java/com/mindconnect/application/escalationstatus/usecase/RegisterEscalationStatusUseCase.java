package com.mindconnect.application.escalationstatus.usecase;

import com.mindconnect.application.escalationstatus.command.RegisterEscalationStatusCommand;
import com.mindconnect.application.escalationstatus.dto.EscalationStatusResponse;
import com.mindconnect.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.mindconnect.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class RegisterEscalationStatusUseCase {

    private final EscalationStatusRepository repository;

    public RegisterEscalationStatusUseCase(EscalationStatusRepository repository) {
        this.repository = repository;
    }

    public EscalationStatusResponse execute(RegisterEscalationStatusCommand command) {
        EscalationStatus aggregate = EscalationStatus.register(
                command.nameStatus());
        EscalationStatus saved = repository.save(aggregate);
        return EscalationStatusResponse.fromDomain(saved);
    }
}
