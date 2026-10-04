package com.mindconnect.infrastructure.citymunicipality.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de city_municipalities.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateCityMunicipalityRequest(
        @NotBlank @Size(max = 50) String nameCity,
        @NotBlank @Size(max = 10) String codeCity,
        @NotBlank @Size(max = 100) String description,
        @NotNull UUID regionId
) {
}
