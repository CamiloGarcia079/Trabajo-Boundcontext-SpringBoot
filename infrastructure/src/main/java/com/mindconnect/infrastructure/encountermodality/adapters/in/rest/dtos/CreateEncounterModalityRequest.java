package com.mindconnect.infrastructure.encountermodality.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para crear un registro de encounter_modalities.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateEncounterModalityRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name
) {
}
