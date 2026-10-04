package com.mindconnect.infrastructure.professionalstudy.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import com.mindconnect.infrastructure.professionalstudy.adapters.in.rest.controllers.ProfessionalStudyController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de professional_studies en una respuesta 404.
 * Solo aplica a ProfessionalStudyController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ProfessionalStudyController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProfessionalStudyExceptionHandler {

    @ExceptionHandler(ProfessionalStudyNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ProfessionalStudyNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
