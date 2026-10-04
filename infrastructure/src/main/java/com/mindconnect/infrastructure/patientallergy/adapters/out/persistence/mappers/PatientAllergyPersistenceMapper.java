package com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.mappers;

import com.mindconnect.domain.patientallergy.model.aggregate.PatientAllergy;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de patient_allergies.
 */
public class PatientAllergyPersistenceMapper {

    public PatientAllergyJpaEntity toJpa(PatientAllergy domain) {
        if (domain == null) {
            return null;
        }

        PatientAllergyJpaEntity jpa = new PatientAllergyJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setPatientId(domain.patientId());
        jpa.setSubstance(domain.substance());
        jpa.setReaction(domain.reaction());
        jpa.setSeverity(domain.severity());
        jpa.setActive(domain.active());
        jpa.setRecordedAt(domain.recordedAt());
        jpa.setRecordedBy(domain.recordedBy());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public PatientAllergy toDomain(PatientAllergyJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return PatientAllergy.restore(
                new PatientAllergyId(jpa.getId()),
                jpa.getPatientId(), jpa.getSubstance(), jpa.getReaction(), jpa.getSeverity(), jpa.getActive(), jpa.getRecordedAt(), jpa.getRecordedBy(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
