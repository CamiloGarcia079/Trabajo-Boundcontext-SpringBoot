package com.mindconnect.infrastructure.medicationroute.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de medication_routes.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateMedicationRouteRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name
) {
}
