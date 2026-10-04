package com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.mappers;

import com.mindconnect.domain.patientcontact.model.aggregate.PatientContact;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;
import com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de patient_contacts.
 */
public class PatientContactPersistenceMapper {

    public PatientContactJpaEntity toJpa(PatientContact domain) {
        if (domain == null) {
            return null;
        }

        PatientContactJpaEntity jpa = new PatientContactJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setContactId(domain.contactId());
        jpa.setPatientId(domain.patientId());
        jpa.setIsPrimaryContact(domain.isPrimaryContact());
        jpa.setIsEmergencyContact(domain.isEmergencyContact());
        jpa.setRelationshipTypeId(domain.relationshipTypeId());

        return jpa;
    }

    public PatientContact toDomain(PatientContactJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return PatientContact.restore(
                new PatientContactId(jpa.getId()),
                jpa.getContactId(), jpa.getPatientId(), jpa.getIsPrimaryContact(), jpa.getIsEmergencyContact(), jpa.getRelationshipTypeId());
    }
}
