package com.mindconnect.application.patientcontact.dto;

import java.util.UUID;

import com.mindconnect.domain.patientcontact.model.aggregate.PatientContact;

public record PatientContactResponse(
        UUID id,
        UUID contactId,
        UUID patientId,
        boolean isPrimaryContact,
        boolean isEmergencyContact,
        UUID relationshipTypeId
) {

    public static PatientContactResponse fromDomain(PatientContact aggregate) {
        return new PatientContactResponse(
                aggregate.id().value(),
                aggregate.contactId(), aggregate.patientId(), aggregate.isPrimaryContact(), aggregate.isEmergencyContact(), aggregate.relationshipTypeId());
    }
}
