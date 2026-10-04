package com.mindconnect.infrastructure.professionaltype.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import com.mindconnect.infrastructure.professionaltype.adapters.in.rest.controllers.ProfessionalTypeController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de professional_types en una respuesta 404.
 * Solo aplica a ProfessionalTypeController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ProfessionalTypeController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProfessionalTypeExceptionHandler {

    @ExceptionHandler(ProfessionalTypeNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ProfessionalTypeNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
