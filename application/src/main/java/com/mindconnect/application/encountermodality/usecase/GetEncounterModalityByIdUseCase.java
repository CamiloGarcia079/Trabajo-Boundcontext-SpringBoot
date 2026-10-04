package com.mindconnect.application.encountermodality.usecase;

import com.mindconnect.application.encountermodality.dto.EncounterModalityResponse;
import com.mindconnect.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.mindconnect.domain.encountermodality.port.repository.EncounterModalityRepository;

public class GetEncounterModalityByIdUseCase {

    private final EncounterModalityRepository repository;

    public GetEncounterModalityByIdUseCase(EncounterModalityRepository repository) {
        this.repository = repository;
    }

    public EncounterModalityResponse execute(EncounterModalityId id) {
        return repository.findById(id)
                .map(EncounterModalityResponse::fromDomain)
                .orElseThrow(() -> new EncounterModalityNotFoundApplicationException(id));
    }
}
