package com.mindconnect.application.escalationstatus.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.mindconnect.domain.escalationstatus.event.EscalationStatusDeletedEvent;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.mindconnect.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class DeleteEscalationStatusUseCase {

    private final EscalationStatusRepository repository;

    public DeleteEscalationStatusUseCase(EscalationStatusRepository repository) {
        this.repository = repository;
    }

    public EscalationStatusDeletedEvent execute(EscalationStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EscalationStatusNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new EscalationStatusDeletedEvent(id, LocalDateTime.now());
    }
}
