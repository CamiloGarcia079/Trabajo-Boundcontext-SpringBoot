package com.mindconnect.application.escalationstatus.usecase;

import com.mindconnect.application.escalationstatus.dto.EscalationStatusResponse;
import com.mindconnect.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.mindconnect.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class GetEscalationStatusByIdUseCase {

    private final EscalationStatusRepository repository;

    public GetEscalationStatusByIdUseCase(EscalationStatusRepository repository) {
        this.repository = repository;
    }

    public EscalationStatusResponse execute(EscalationStatusId id) {
        return repository.findById(id)
                .map(EscalationStatusResponse::fromDomain)
                .orElseThrow(() -> new EscalationStatusNotFoundApplicationException(id));
    }
}
