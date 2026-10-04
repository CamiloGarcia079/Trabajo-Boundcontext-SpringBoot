package com.mindconnect.application.medicationroute.command;


import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;

public record UpdateMedicationRouteCommand(
        MedicationRouteId id,
        String code,
        String name
) {
}
