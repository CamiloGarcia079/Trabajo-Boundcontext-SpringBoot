package com.mindconnect.application.medicationroute.command;


public record RegisterMedicationRouteCommand(
        String code,
        String name
) {
}
