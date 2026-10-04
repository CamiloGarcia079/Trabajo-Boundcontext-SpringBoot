package com.mindconnect.application.treatmentstatus.usecase;

import com.mindconnect.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.mindconnect.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.mindconnect.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class GetTreatmentStatusByIdUseCase {

    private final TreatmentStatusRepository repository;

    public GetTreatmentStatusByIdUseCase(TreatmentStatusRepository repository) {
        this.repository = repository;
    }

    public TreatmentStatusResponse execute(TreatmentStatusId id) {
        return repository.findById(id)
                .map(TreatmentStatusResponse::fromDomain)
                .orElseThrow(() -> new TreatmentStatusNotFoundApplicationException(id));
    }
}
