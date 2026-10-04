package com.mindconnect.application.treatmentplan.usecase;

import com.mindconnect.application.treatmentplan.command.UpdateTreatmentPlanCommand;
import com.mindconnect.application.treatmentplan.dto.TreatmentPlanResponse;
import com.mindconnect.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import com.mindconnect.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.mindconnect.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class UpdateTreatmentPlanUseCase {

    private final TreatmentPlanRepository repository;

    public UpdateTreatmentPlanUseCase(TreatmentPlanRepository repository) {
        this.repository = repository;
    }

    public TreatmentPlanResponse execute(UpdateTreatmentPlanCommand command) {
        TreatmentPlan aggregate = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentPlanNotFoundApplicationException(command.id()));
        aggregate.update(
                command.encounterId(), command.professionalId(), command.title(), command.description(), command.startDate(), command.endDate(), command.treatmentStatusId());
        TreatmentPlan saved = repository.save(aggregate);
        return TreatmentPlanResponse.fromDomain(saved);
    }
}
