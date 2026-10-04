package com.mindconnect.infrastructure.emailcontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de email_contacts.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateEmailContactRequest(
        @NotNull UUID contactId,
        @NotBlank @Size(max = 150) String email,
        @NotBlank String notes
) {
}
