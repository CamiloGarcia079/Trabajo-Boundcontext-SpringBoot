package com.mindconnect.infrastructure.documenttype.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.mindconnect.infrastructure.documenttype.adapters.in.rest.controllers.DocumentTypeController;
import com.mindconnect.infrastructure.common.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de document_types en una respuesta 404.
 * Solo aplica a DocumentTypeController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = DocumentTypeController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DocumentTypeExceptionHandler {

    @ExceptionHandler(DocumentTypeNotFoundApplicationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(DocumentTypeNotFoundApplicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
