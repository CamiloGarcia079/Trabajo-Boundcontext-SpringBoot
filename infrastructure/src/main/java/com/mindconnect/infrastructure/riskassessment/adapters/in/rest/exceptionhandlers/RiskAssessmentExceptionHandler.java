package com.mindconnect.infrastructure.riskassessment.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import com.mindconnect.infrastructure.riskassessment.adapters.in.rest.controllers.RiskAssessmentController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de risk_assessments en una respuesta 404.
 * Solo aplica a RiskAssessmentController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = RiskAssessmentController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RiskAssessmentExceptionHandler {

    @ExceptionHandler(RiskAssessmentNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(RiskAssessmentNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
