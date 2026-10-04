package com.mindconnect.application.patient.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.patient.model.aggregate.Patient;

public record PatientResponse(
        UUID id,
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        LocalDate birthDate,
        UUID biologicalSexId,
        UUID genderIdentity,
        String email,
        String phone,
        String address,
        boolean active,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy,
        UUID cityId
) {

    public static PatientResponse fromDomain(Patient aggregate) {
        return new PatientResponse(
                aggregate.id().value(),
                aggregate.documentTypeId(), aggregate.documentNumber(), aggregate.firstName(), aggregate.middleName(), aggregate.lastName(), aggregate.secondLastName(), aggregate.birthDate(), aggregate.biologicalSexId(), aggregate.genderIdentity(), aggregate.email(), aggregate.phone(), aggregate.address(), aggregate.active(), aggregate.createdAt(), aggregate.createdBy(), aggregate.updatedAt(), aggregate.updatedBy(), aggregate.cityId());
    }
}
