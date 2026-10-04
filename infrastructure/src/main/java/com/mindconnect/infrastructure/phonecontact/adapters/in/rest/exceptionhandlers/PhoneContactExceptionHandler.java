package com.mindconnect.infrastructure.phonecontact.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import com.mindconnect.infrastructure.phonecontact.adapters.in.rest.controllers.PhoneContactController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de phone_contacts en una respuesta 404.
 * Solo aplica a PhoneContactController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = PhoneContactController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PhoneContactExceptionHandler {

    @ExceptionHandler(PhoneContactNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(PhoneContactNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
