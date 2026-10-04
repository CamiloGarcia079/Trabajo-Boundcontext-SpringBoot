package com.mindconnect.application.emailcontact.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import com.mindconnect.domain.emailcontact.event.EmailContactDeletedEvent;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;
import com.mindconnect.domain.emailcontact.port.repository.EmailContactRepository;

public class DeleteEmailContactUseCase {

    private final EmailContactRepository repository;

    public DeleteEmailContactUseCase(EmailContactRepository repository) {
        this.repository = repository;
    }

    public EmailContactDeletedEvent execute(EmailContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EmailContactNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new EmailContactDeletedEvent(id, LocalDateTime.now());
    }
}
