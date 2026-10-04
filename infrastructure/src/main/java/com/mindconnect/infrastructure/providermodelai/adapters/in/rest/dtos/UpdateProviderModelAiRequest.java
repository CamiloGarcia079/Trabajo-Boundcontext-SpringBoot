package com.mindconnect.infrastructure.providermodelai.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de provider_models_ai.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateProviderModelAiRequest(
        @NotBlank @Size(max = 100) String nameProviderAi,
        @NotBlank @Size(max = 150) String razonSocial,
        @NotBlank String sitioWeb
) {
}
