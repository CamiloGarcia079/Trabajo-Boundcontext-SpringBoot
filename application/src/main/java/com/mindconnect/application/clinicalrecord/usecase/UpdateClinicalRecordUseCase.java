package com.mindconnect.application.clinicalrecord.usecase;

import com.mindconnect.application.clinicalrecord.command.UpdateClinicalRecordCommand;
import com.mindconnect.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.mindconnect.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.mindconnect.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class UpdateClinicalRecordUseCase {

    private final ClinicalRecordRepository repository;

    public UpdateClinicalRecordUseCase(ClinicalRecordRepository repository) {
        this.repository = repository;
    }

    public ClinicalRecordResponse execute(UpdateClinicalRecordCommand command) {
        ClinicalRecord aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ClinicalRecordNotFoundApplicationException(command.id()));
        aggregate.update(
                command.patientId(), command.creationDate(), command.recordNumber(), command.openedAt(), command.closedAt(), command.statusId());
        ClinicalRecord saved = repository.save(aggregate);
        return ClinicalRecordResponse.fromDomain(saved);
    }
}
