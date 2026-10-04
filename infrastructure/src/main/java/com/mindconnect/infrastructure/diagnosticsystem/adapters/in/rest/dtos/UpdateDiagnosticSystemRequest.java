package com.mindconnect.infrastructure.diagnosticsystem.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de diagnostic_systems.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateDiagnosticSystemRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotBlank @Size(max = 20) String version
) {
}
