package com.mindconnect.infrastructure.documenttype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para crear un registro de document_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateDocumentTypeRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name
) {
}
