package com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.mappers;

import com.mindconnect.domain.phonecontact.model.aggregate.PhoneContact;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;
import com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de phone_contacts.
 */
public class PhoneContactPersistenceMapper {

    public PhoneContactJpaEntity toJpa(PhoneContact domain) {
        if (domain == null) {
            return null;
        }

        PhoneContactJpaEntity jpa = new PhoneContactJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setContactId(domain.contactId());
        jpa.setPhone(domain.phone());
        jpa.setNotes(domain.notes());

        return jpa;
    }

    public PhoneContact toDomain(PhoneContactJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return PhoneContact.restore(
                new PhoneContactId(jpa.getId()),
                jpa.getContactId(), jpa.getPhone(), jpa.getNotes());
    }
}
