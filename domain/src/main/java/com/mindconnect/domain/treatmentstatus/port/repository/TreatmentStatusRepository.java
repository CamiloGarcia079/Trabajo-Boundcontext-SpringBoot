package com.mindconnect.domain.treatmentstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar TreatmentStatus.
 * Lo implementa la infraestructura.
 */
public interface TreatmentStatusRepository {

    TreatmentStatus save(TreatmentStatus aggregate);

    Optional<TreatmentStatus> findById(TreatmentStatusId id);

    List<TreatmentStatus> findAll();

    void delete(TreatmentStatus aggregate);
}
