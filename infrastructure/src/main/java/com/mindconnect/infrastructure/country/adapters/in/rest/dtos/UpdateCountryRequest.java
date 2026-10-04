package com.mindconnect.infrastructure.country.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de countries.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateCountryRequest(
        @NotBlank @Size(max = 50) String nameCountry,
        @NotBlank @Size(max = 10) String codeCountry,
        @NotBlank @Size(max = 100) String description,
        @NotBlank @Size(max = 5) String telephonePrefix
) {
}
