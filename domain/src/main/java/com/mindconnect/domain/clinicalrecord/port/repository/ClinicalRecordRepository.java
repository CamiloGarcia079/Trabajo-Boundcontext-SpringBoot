package com.mindconnect.domain.clinicalrecord.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ClinicalRecord.
 * Lo implementa la infraestructura.
 */
public interface ClinicalRecordRepository {

    ClinicalRecord save(ClinicalRecord aggregate);

    Optional<ClinicalRecord> findById(ClinicalRecordId id);

    List<ClinicalRecord> findAll();

    void delete(ClinicalRecord aggregate);
}
