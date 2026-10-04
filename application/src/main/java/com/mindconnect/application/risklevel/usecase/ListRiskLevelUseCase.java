package com.mindconnect.application.risklevel.usecase;

import java.util.List;

import com.mindconnect.application.risklevel.dto.RiskLevelResponse;
import com.mindconnect.domain.risklevel.port.repository.RiskLevelRepository;

public class ListRiskLevelUseCase {

    private final RiskLevelRepository repository;

    public ListRiskLevelUseCase(RiskLevelRepository repository) {
        this.repository = repository;
    }

    public List<RiskLevelResponse> execute() {
        return repository.findAll()
                .stream()
                .map(RiskLevelResponse::fromDomain)
                .toList();
    }
}
