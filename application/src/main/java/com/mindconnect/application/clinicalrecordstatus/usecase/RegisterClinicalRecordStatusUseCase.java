package com.mindconnect.application.clinicalrecordstatus.usecase;

import com.mindconnect.application.clinicalrecordstatus.command.RegisterClinicalRecordStatusCommand;
import com.mindconnect.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.mindconnect.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class RegisterClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;

    public RegisterClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        this.repository = repository;
    }

    public ClinicalRecordStatusResponse execute(RegisterClinicalRecordStatusCommand command) {
        ClinicalRecordStatus aggregate = ClinicalRecordStatus.register(
                command.code(), command.name());
        ClinicalRecordStatus saved = repository.save(aggregate);
        return ClinicalRecordStatusResponse.fromDomain(saved);
    }
}
