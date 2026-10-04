package com.mindconnect.infrastructure.contact.adapters.out.persistence.mappers;

import com.mindconnect.domain.contact.model.aggregate.Contact;
import com.mindconnect.domain.contact.model.valueobject.ContactId;
import com.mindconnect.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de contacts.
 */
public class ContactPersistenceMapper {

    public ContactJpaEntity toJpa(Contact domain) {
        if (domain == null) {
            return null;
        }

        ContactJpaEntity jpa = new ContactJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setFullName(domain.fullName());
        jpa.setEmail(domain.email());
        jpa.setNotes(domain.notes());
        jpa.setCityId(domain.cityId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setCreatedBy(domain.createdBy());
        jpa.setUpdatedAt(domain.updatedAt());
        jpa.setUpdatedBy(domain.updatedBy());

        return jpa;
    }

    public Contact toDomain(ContactJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return Contact.restore(
                new ContactId(jpa.getId()),
                jpa.getFullName(), jpa.getEmail(), jpa.getNotes(), jpa.getCityId(), jpa.getCreatedAt(), jpa.getCreatedBy(), jpa.getUpdatedAt(), jpa.getUpdatedBy());
    }
}
