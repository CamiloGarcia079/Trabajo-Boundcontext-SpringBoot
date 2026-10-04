package com.mindconnect.infrastructure.patient.adapters.out.persistence.mappers;

import com.mindconnect.domain.patient.model.aggregate.Patient;
import com.mindconnect.domain.patient.model.valueobject.PatientId;
import com.mindconnect.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de patients.
 */
public class PatientPersistenceMapper {

    public PatientJpaEntity toJpa(Patient domain) {
        if (domain == null) {
            return null;
        }

        PatientJpaEntity jpa = new PatientJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setDocumentTypeId(domain.documentTypeId());
        jpa.setDocumentNumber(domain.documentNumber());
        jpa.setFirstName(domain.firstName());
        jpa.setMiddleName(domain.middleName());
        jpa.setLastName(domain.lastName());
        jpa.setSecondLastName(domain.secondLastName());
        jpa.setBirthDate(domain.birthDate());
        jpa.setBiologicalSexId(domain.biologicalSexId());
        jpa.setGenderIdentity(domain.genderIdentity());
        jpa.setEmail(domain.email());
        jpa.setPhone(domain.phone());
        jpa.setAddress(domain.address());
        jpa.setActive(domain.active());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setCreatedBy(domain.createdBy());
        jpa.setUpdatedAt(domain.updatedAt());
        jpa.setUpdatedBy(domain.updatedBy());
        jpa.setCityId(domain.cityId());

        return jpa;
    }

    public Patient toDomain(PatientJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return Patient.restore(
                new PatientId(jpa.getId()),
                jpa.getDocumentTypeId(), jpa.getDocumentNumber(), jpa.getFirstName(), jpa.getMiddleName(), jpa.getLastName(), jpa.getSecondLastName(), jpa.getBirthDate(), jpa.getBiologicalSexId(), jpa.getGenderIdentity(), jpa.getEmail(), jpa.getPhone(), jpa.getAddress(), jpa.getActive(), jpa.getCreatedAt(), jpa.getCreatedBy(), jpa.getUpdatedAt(), jpa.getUpdatedBy(), jpa.getCityId());
    }
}
