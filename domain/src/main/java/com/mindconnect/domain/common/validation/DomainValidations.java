package com.mindconnect.domain.common.validation;

import com.mindconnect.domain.common.exception.DomainException;

/**
 * Validaciones pequeñas que reutilizan los agregados del dominio.
 */
public final class DomainValidations {

    private DomainValidations() {
    }

    /**
     * Falla si el valor es nulo.
     *
     * @param value valor a revisar
     * @param field nombre del campo, para el mensaje de error
     */
    public static void required(Object value, String field) {
        if (value == null) {
            throw new DomainException("El campo '" + field + "' es obligatorio");
        }
    }
}
