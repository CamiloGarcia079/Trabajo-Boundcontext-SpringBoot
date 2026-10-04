package com.mindconnect.application.encountertype.usecase;

import com.mindconnect.application.encountertype.dto.EncounterTypeResponse;
import com.mindconnect.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;
import com.mindconnect.domain.encountertype.port.repository.EncounterTypeRepository;

public class GetEncounterTypeByIdUseCase {

    private final EncounterTypeRepository repository;

    public GetEncounterTypeByIdUseCase(EncounterTypeRepository repository) {
        this.repository = repository;
    }

    public EncounterTypeResponse execute(EncounterTypeId id) {
        return repository.findById(id)
                .map(EncounterTypeResponse::fromDomain)
                .orElseThrow(() -> new EncounterTypeNotFoundApplicationException(id));
    }
}
