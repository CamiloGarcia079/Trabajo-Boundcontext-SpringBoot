package com.mindconnect.application.risklevel.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.mindconnect.domain.risklevel.event.RiskLevelDeletedEvent;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;
import com.mindconnect.domain.risklevel.port.repository.RiskLevelRepository;

public class DeleteRiskLevelUseCase {

    private final RiskLevelRepository repository;

    public DeleteRiskLevelUseCase(RiskLevelRepository repository) {
        this.repository = repository;
    }

    public RiskLevelDeletedEvent execute(RiskLevelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new RiskLevelNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new RiskLevelDeletedEvent(id, LocalDateTime.now());
    }
}
