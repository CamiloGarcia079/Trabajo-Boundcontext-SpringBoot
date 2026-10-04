package com.mindconnect.application.patientcontact.command;

import java.util.UUID;

public record RegisterPatientContactCommand(
        UUID contactId,
        UUID patientId,
        boolean isPrimaryContact,
        boolean isEmergencyContact,
        UUID relationshipTypeId
) {
}
