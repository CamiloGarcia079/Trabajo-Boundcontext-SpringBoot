package com.mindconnect.infrastructure.emailcontact.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import com.mindconnect.infrastructure.emailcontact.adapters.in.rest.controllers.EmailContactController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de email_contacts en una respuesta 404.
 * Solo aplica a EmailContactController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = EmailContactController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EmailContactExceptionHandler {

    @ExceptionHandler(EmailContactNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EmailContactNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
