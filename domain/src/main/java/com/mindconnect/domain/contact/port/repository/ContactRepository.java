package com.mindconnect.domain.contact.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.contact.model.aggregate.Contact;
import com.mindconnect.domain.contact.model.valueobject.ContactId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar Contact.
 * Lo implementa la infraestructura.
 */
public interface ContactRepository {

    Contact save(Contact aggregate);

    Optional<Contact> findById(ContactId id);

    List<Contact> findAll();

    void delete(Contact aggregate);
}
