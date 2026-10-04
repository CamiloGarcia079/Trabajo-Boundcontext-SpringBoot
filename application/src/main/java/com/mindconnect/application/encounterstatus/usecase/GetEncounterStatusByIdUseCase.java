package com.mindconnect.application.encounterstatus.usecase;

import com.mindconnect.application.encounterstatus.dto.EncounterStatusResponse;
import com.mindconnect.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.mindconnect.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class GetEncounterStatusByIdUseCase {

    private final EncounterStatusRepository repository;

    public GetEncounterStatusByIdUseCase(EncounterStatusRepository repository) {
        this.repository = repository;
    }

    public EncounterStatusResponse execute(EncounterStatusId id) {
        return repository.findById(id)
                .map(EncounterStatusResponse::fromDomain)
                .orElseThrow(() -> new EncounterStatusNotFoundApplicationException(id));
    }
}
