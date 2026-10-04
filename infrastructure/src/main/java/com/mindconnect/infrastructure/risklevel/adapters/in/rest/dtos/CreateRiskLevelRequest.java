package com.mindconnect.infrastructure.risklevel.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para crear un registro de risk_levels.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateRiskLevelRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Integer severity
) {
}
