package com.mindconnect.domain.patientcontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.patientcontact.model.aggregate.PatientContact;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar PatientContact.
 * Lo implementa la infraestructura.
 */
public interface PatientContactRepository {

    PatientContact save(PatientContact aggregate);

    Optional<PatientContact> findById(PatientContactId id);

    List<PatientContact> findAll();

    void delete(PatientContact aggregate);
}
