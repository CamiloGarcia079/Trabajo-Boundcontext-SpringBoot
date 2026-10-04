package com.mindconnect.application.risklevel.usecase;

import com.mindconnect.application.risklevel.command.RegisterRiskLevelCommand;
import com.mindconnect.application.risklevel.dto.RiskLevelResponse;
import com.mindconnect.domain.risklevel.model.aggregate.RiskLevel;
import com.mindconnect.domain.risklevel.port.repository.RiskLevelRepository;

public class RegisterRiskLevelUseCase {

    private final RiskLevelRepository repository;

    public RegisterRiskLevelUseCase(RiskLevelRepository repository) {
        this.repository = repository;
    }

    public RiskLevelResponse execute(RegisterRiskLevelCommand command) {
        RiskLevel aggregate = RiskLevel.register(
                command.code(), command.name(), command.severity());
        RiskLevel saved = repository.save(aggregate);
        return RiskLevelResponse.fromDomain(saved);
    }
}
