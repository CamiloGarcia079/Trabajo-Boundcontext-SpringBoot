package com.mindconnect.domain.citymunicipality.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public class CityMunicipalityNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public CityMunicipalityNotFoundException(CityMunicipalityId id) {
        super("CityMunicipality with id " + id.value() + " was not found.");
    }
}
