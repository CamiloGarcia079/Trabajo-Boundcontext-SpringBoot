package com.mindconnect.application.treatmentgoal.usecase;

import com.mindconnect.application.treatmentgoal.command.UpdateTreatmentGoalCommand;
import com.mindconnect.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.mindconnect.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class UpdateTreatmentGoalUseCase {

    private final TreatmentGoalRepository repository;

    public UpdateTreatmentGoalUseCase(TreatmentGoalRepository repository) {
        this.repository = repository;
    }

    public TreatmentGoalResponse execute(UpdateTreatmentGoalCommand command) {
        TreatmentGoal aggregate = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentGoalNotFoundApplicationException(command.id()));
        aggregate.update(
                command.treatmentPlanId(), command.description(), command.targetDate(), command.completedAt(), command.notes(), command.treatmentGoalId());
        TreatmentGoal saved = repository.save(aggregate);
        return TreatmentGoalResponse.fromDomain(saved);
    }
}
