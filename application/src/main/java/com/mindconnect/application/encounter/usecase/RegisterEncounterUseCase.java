package com.mindconnect.application.encounter.usecase;

import com.mindconnect.application.encounter.command.RegisterEncounterCommand;
import com.mindconnect.application.encounter.dto.EncounterResponse;
import com.mindconnect.domain.encounter.model.aggregate.Encounter;
import com.mindconnect.domain.encounter.port.repository.EncounterRepository;

public class RegisterEncounterUseCase {

    private final EncounterRepository repository;

    public RegisterEncounterUseCase(EncounterRepository repository) {
        this.repository = repository;
    }

    public EncounterResponse execute(RegisterEncounterCommand command) {
        Encounter aggregate = Encounter.register(
                command.clinicalRecordId(), command.professionalId(), command.encounterTypeId(), command.startedAt(), command.endedAt(), command.reasonForVisit(), command.currentCondition(), command.modalityId(), command.statusId(), command.createdBy(), command.updatedBy());
        Encounter saved = repository.save(aggregate);
        return EncounterResponse.fromDomain(saved);
    }
}
