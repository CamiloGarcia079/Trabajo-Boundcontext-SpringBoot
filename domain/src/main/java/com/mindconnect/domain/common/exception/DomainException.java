package com.mindconnect.domain.common.exception;

/**
 * Error de una regla del dominio (por ejemplo, un campo obligatorio que llega vacío).
 */
public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DomainException(String message) {
        super(message);
    }
}
