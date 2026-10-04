package com.mindconnect.domain.citymunicipality.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar CityMunicipality.
 * Lo implementa la infraestructura.
 */
public interface CityMunicipalityRepository {

    CityMunicipality save(CityMunicipality aggregate);

    Optional<CityMunicipality> findById(CityMunicipalityId id);

    List<CityMunicipality> findAll();

    void delete(CityMunicipality aggregate);
}
