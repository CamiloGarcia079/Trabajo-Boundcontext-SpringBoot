package com.mindconnect.application.treatmentgoalstatus.usecase;

import com.mindconnect.application.treatmentgoalstatus.command.RegisterTreatmentGoalStatusCommand;
import com.mindconnect.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.mindconnect.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.mindconnect.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class RegisterTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository repository;

    public RegisterTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalStatusResponse execute(RegisterTreatmentGoalStatusCommand command) {
        TreatmentGoalStatus aggregate = TreatmentGoalStatus.register(
                command.code(), command.name());
        TreatmentGoalStatus saved = repository.save(aggregate);
        return TreatmentGoalStatusResponse.fromDomain(saved);
    }
}
