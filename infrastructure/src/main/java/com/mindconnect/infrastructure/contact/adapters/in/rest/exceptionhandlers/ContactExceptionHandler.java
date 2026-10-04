package com.mindconnect.infrastructure.contact.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.contact.exception.ContactNotFoundApplicationException;
import com.mindconnect.infrastructure.contact.adapters.in.rest.controllers.ContactController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de contacts en una respuesta 404.
 * Solo aplica a ContactController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ContactController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContactExceptionHandler {

    @ExceptionHandler(ContactNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ContactNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
