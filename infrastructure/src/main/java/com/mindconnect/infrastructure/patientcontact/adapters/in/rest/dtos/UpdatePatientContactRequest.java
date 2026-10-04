package com.mindconnect.infrastructure.patientcontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de patient_contacts.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdatePatientContactRequest(
        @NotNull UUID contactId,
        @NotNull UUID patientId,
        @NotNull Boolean isPrimaryContact,
        @NotNull Boolean isEmergencyContact,
        @NotNull UUID relationshipTypeId
) {
}
