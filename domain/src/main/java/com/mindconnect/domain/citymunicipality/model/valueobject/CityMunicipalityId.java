package com.mindconnect.domain.citymunicipality.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de CityMunicipality. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record CityMunicipalityId(UUID value) {

    public CityMunicipalityId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static CityMunicipalityId generate() {
        return new CityMunicipalityId(UUID.randomUUID());
    }
}
