package com.mindconnect.application.treatmentgoalstatus.usecase;

import com.mindconnect.application.treatmentgoalstatus.command.UpdateTreatmentGoalStatusCommand;
import com.mindconnect.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.mindconnect.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.mindconnect.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class UpdateTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository repository;

    public UpdateTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalStatusResponse execute(UpdateTreatmentGoalStatusCommand command) {
        TreatmentGoalStatus aggregate = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name());
        TreatmentGoalStatus saved = repository.save(aggregate);
        return TreatmentGoalStatusResponse.fromDomain(saved);
    }
}
