package com.mindconnect.application.clinicalrecordstatus.command;


public record RegisterClinicalRecordStatusCommand(
        String code,
        String name
) {
}
