package com.mindconnect.application.patientcontact.command;

import java.util.UUID;

import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;

public record UpdatePatientContactCommand(
        PatientContactId id,
        UUID contactId,
        UUID patientId,
        boolean isPrimaryContact,
        boolean isEmergencyContact,
        UUID relationshipTypeId
) {
}
