package com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.mappers;

import com.mindconnect.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de clinical_records.
 */
public class ClinicalRecordPersistenceMapper {

    public ClinicalRecordJpaEntity toJpa(ClinicalRecord domain) {
        if (domain == null) {
            return null;
        }

        ClinicalRecordJpaEntity jpa = new ClinicalRecordJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setPatientId(domain.patientId());
        jpa.setCreationDate(domain.creationDate());
        jpa.setRecordNumber(domain.recordNumber());
        jpa.setOpenedAt(domain.openedAt());
        jpa.setClosedAt(domain.closedAt());
        jpa.setStatusId(domain.statusId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setCreatedBy(domain.createdBy());

        return jpa;
    }

    public ClinicalRecord toDomain(ClinicalRecordJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ClinicalRecord.restore(
                new ClinicalRecordId(jpa.getId()),
                jpa.getPatientId(), jpa.getCreationDate(), jpa.getRecordNumber(), jpa.getOpenedAt(), jpa.getClosedAt(), jpa.getStatusId(), jpa.getCreatedAt(), jpa.getCreatedBy());
    }
}
