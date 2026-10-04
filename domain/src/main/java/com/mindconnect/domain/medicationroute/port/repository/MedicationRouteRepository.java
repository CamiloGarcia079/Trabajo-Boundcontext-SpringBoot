package com.mindconnect.domain.medicationroute.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.medicationroute.model.aggregate.MedicationRoute;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar MedicationRoute.
 * Lo implementa la infraestructura.
 */
public interface MedicationRouteRepository {

    MedicationRoute save(MedicationRoute aggregate);

    Optional<MedicationRoute> findById(MedicationRouteId id);

    List<MedicationRoute> findAll();

    void delete(MedicationRoute aggregate);
}
