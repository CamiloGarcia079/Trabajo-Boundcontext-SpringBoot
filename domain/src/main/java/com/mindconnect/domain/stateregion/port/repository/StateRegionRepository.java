package com.mindconnect.domain.stateregion.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.stateregion.model.aggregate.StateRegion;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar StateRegion.
 * Lo implementa la infraestructura.
 */
public interface StateRegionRepository {

    StateRegion save(StateRegion aggregate);

    Optional<StateRegion> findById(StateRegionId id);

    List<StateRegion> findAll();

    void delete(StateRegion aggregate);
}
