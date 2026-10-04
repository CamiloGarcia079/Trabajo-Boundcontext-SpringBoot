package com.mindconnect.domain.phonecontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.phonecontact.model.aggregate.PhoneContact;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar PhoneContact.
 * Lo implementa la infraestructura.
 */
public interface PhoneContactRepository {

    PhoneContact save(PhoneContact aggregate);

    Optional<PhoneContact> findById(PhoneContactId id);

    List<PhoneContact> findAll();

    void delete(PhoneContact aggregate);
}
