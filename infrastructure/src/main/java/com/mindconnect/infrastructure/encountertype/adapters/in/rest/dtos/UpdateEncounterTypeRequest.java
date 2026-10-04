package com.mindconnect.infrastructure.encountertype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de encounter_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateEncounterTypeRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name
) {
}
