package com.mindconnect.application.contact.usecase;

import com.mindconnect.application.contact.dto.ContactResponse;
import com.mindconnect.application.contact.exception.ContactNotFoundApplicationException;
import com.mindconnect.domain.contact.model.valueobject.ContactId;
import com.mindconnect.domain.contact.port.repository.ContactRepository;

public class GetContactByIdUseCase {

    private final ContactRepository repository;

    public GetContactByIdUseCase(ContactRepository repository) {
        this.repository = repository;
    }

    public ContactResponse execute(ContactId id) {
        return repository.findById(id)
                .map(ContactResponse::fromDomain)
                .orElseThrow(() -> new ContactNotFoundApplicationException(id));
    }
}
