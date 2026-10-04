package com.mindconnect.application.risklevel.usecase;

import com.mindconnect.application.risklevel.command.UpdateRiskLevelCommand;
import com.mindconnect.application.risklevel.dto.RiskLevelResponse;
import com.mindconnect.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.mindconnect.domain.risklevel.model.aggregate.RiskLevel;
import com.mindconnect.domain.risklevel.port.repository.RiskLevelRepository;

public class UpdateRiskLevelUseCase {

    private final RiskLevelRepository repository;

    public UpdateRiskLevelUseCase(RiskLevelRepository repository) {
        this.repository = repository;
    }

    public RiskLevelResponse execute(UpdateRiskLevelCommand command) {
        RiskLevel aggregate = repository.findById(command.id())
                .orElseThrow(() -> new RiskLevelNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name(), command.severity());
        RiskLevel saved = repository.save(aggregate);
        return RiskLevelResponse.fromDomain(saved);
    }
}
