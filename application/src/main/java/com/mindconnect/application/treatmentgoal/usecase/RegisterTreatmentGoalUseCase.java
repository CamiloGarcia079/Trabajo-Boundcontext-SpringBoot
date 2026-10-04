package com.mindconnect.application.treatmentgoal.usecase;

import com.mindconnect.application.treatmentgoal.command.RegisterTreatmentGoalCommand;
import com.mindconnect.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.mindconnect.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class RegisterTreatmentGoalUseCase {

    private final TreatmentGoalRepository repository;

    public RegisterTreatmentGoalUseCase(TreatmentGoalRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalResponse execute(RegisterTreatmentGoalCommand command) {
        TreatmentGoal aggregate = TreatmentGoal.register(
                command.treatmentPlanId(), command.description(), command.targetDate(), command.completedAt(), command.notes(), command.treatmentGoalId());
        TreatmentGoal saved = repository.save(aggregate);
        return TreatmentGoalResponse.fromDomain(saved);
    }
}
