package com.mindconnect.application.encounter.usecase;

import com.mindconnect.application.encounter.command.UpdateEncounterCommand;
import com.mindconnect.application.encounter.dto.EncounterResponse;
import com.mindconnect.application.encounter.exception.EncounterNotFoundApplicationException;
import com.mindconnect.domain.encounter.model.aggregate.Encounter;
import com.mindconnect.domain.encounter.port.repository.EncounterRepository;

public class UpdateEncounterUseCase {

    private final EncounterRepository repository;

    public UpdateEncounterUseCase(EncounterRepository repository) {
        this.repository = repository;
    }

    public EncounterResponse execute(UpdateEncounterCommand command) {
        Encounter aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EncounterNotFoundApplicationException(command.id()));
        aggregate.update(
                command.clinicalRecordId(), command.professionalId(), command.encounterTypeId(), command.startedAt(), command.endedAt(), command.reasonForVisit(), command.currentCondition(), command.modalityId(), command.statusId(), command.updatedBy());
        Encounter saved = repository.save(aggregate);
        return EncounterResponse.fromDomain(saved);
    }
}
