package com.mindconnect.application.clinicalrecordstatus.usecase;

import com.mindconnect.application.clinicalrecordstatus.command.UpdateClinicalRecordStatusCommand;
import com.mindconnect.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.mindconnect.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class UpdateClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;

    public UpdateClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        this.repository = repository;
    }

    public ClinicalRecordStatusResponse execute(UpdateClinicalRecordStatusCommand command) {
        ClinicalRecordStatus aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name());
        ClinicalRecordStatus saved = repository.save(aggregate);
        return ClinicalRecordStatusResponse.fromDomain(saved);
    }
}
