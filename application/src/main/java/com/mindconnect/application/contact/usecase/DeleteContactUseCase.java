package com.mindconnect.application.contact.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.contact.exception.ContactNotFoundApplicationException;
import com.mindconnect.domain.contact.event.ContactDeletedEvent;
import com.mindconnect.domain.contact.model.valueobject.ContactId;
import com.mindconnect.domain.contact.port.repository.ContactRepository;

public class DeleteContactUseCase {

    private final ContactRepository repository;

    public DeleteContactUseCase(ContactRepository repository) {
        this.repository = repository;
    }

    public ContactDeletedEvent execute(ContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ContactNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ContactDeletedEvent(id, LocalDateTime.now());
    }
}
