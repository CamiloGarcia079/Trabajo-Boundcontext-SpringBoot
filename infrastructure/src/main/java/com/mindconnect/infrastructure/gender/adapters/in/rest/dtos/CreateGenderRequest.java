package com.mindconnect.infrastructure.gender.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para crear un registro de genders.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateGenderRequest(
        @NotBlank @Size(max = 50) String description
) {
}
