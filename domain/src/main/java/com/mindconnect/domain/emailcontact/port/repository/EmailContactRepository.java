package com.mindconnect.domain.emailcontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.emailcontact.model.aggregate.EmailContact;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar EmailContact.
 * Lo implementa la infraestructura.
 */
public interface EmailContactRepository {

    EmailContact save(EmailContact aggregate);

    Optional<EmailContact> findById(EmailContactId id);

    List<EmailContact> findAll();

    void delete(EmailContact aggregate);
}
