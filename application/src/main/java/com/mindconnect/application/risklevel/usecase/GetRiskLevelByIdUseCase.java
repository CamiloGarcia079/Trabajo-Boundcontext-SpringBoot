package com.mindconnect.application.risklevel.usecase;

import com.mindconnect.application.risklevel.dto.RiskLevelResponse;
import com.mindconnect.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;
import com.mindconnect.domain.risklevel.port.repository.RiskLevelRepository;

public class GetRiskLevelByIdUseCase {

    private final RiskLevelRepository repository;

    public GetRiskLevelByIdUseCase(RiskLevelRepository repository) {
        this.repository = repository;
    }

    public RiskLevelResponse execute(RiskLevelId id) {
        return repository.findById(id)
                .map(RiskLevelResponse::fromDomain)
                .orElseThrow(() -> new RiskLevelNotFoundApplicationException(id));
    }
}
