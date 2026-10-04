package com.mindconnect.infrastructure.assessmenttype.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import com.mindconnect.infrastructure.assessmenttype.adapters.in.rest.controllers.AssessmentTypeController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de assessment_types en una respuesta 404.
 * Solo aplica a AssessmentTypeController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = AssessmentTypeController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AssessmentTypeExceptionHandler {

    @ExceptionHandler(AssessmentTypeNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(AssessmentTypeNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
