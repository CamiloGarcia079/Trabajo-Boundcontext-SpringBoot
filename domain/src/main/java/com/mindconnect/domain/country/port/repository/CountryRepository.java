package com.mindconnect.domain.country.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.country.model.aggregate.Country;
import com.mindconnect.domain.country.model.valueobject.CountryId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar Country.
 * Lo implementa la infraestructura.
 */
public interface CountryRepository {

    Country save(Country aggregate);

    Optional<Country> findById(CountryId id);

    List<Country> findAll();

    void delete(Country aggregate);
}
