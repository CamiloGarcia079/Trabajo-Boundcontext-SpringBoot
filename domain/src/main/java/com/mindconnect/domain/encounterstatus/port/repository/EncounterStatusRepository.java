package com.mindconnect.domain.encounterstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar EncounterStatus.
 * Lo implementa la infraestructura.
 */
public interface EncounterStatusRepository {

    EncounterStatus save(EncounterStatus aggregate);

    Optional<EncounterStatus> findById(EncounterStatusId id);

    List<EncounterStatus> findAll();

    void delete(EncounterStatus aggregate);
}
