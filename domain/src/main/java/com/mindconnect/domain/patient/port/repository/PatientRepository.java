package com.mindconnect.domain.patient.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.patient.model.aggregate.Patient;
import com.mindconnect.domain.patient.model.valueobject.PatientId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar Patient.
 * Lo implementa la infraestructura.
 */
public interface PatientRepository {

    Patient save(Patient aggregate);

    Optional<Patient> findById(PatientId id);

    List<Patient> findAll();

    void delete(Patient aggregate);
}
