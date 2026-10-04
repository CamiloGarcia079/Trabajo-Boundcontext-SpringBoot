package com.mindconnect.application.clinicalrecordstatus.command;


import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public record UpdateClinicalRecordStatusCommand(
        ClinicalRecordStatusId id,
        String code,
        String name
) {
}
