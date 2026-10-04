package com.mindconnect.application.phonecontact.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import com.mindconnect.domain.phonecontact.event.PhoneContactDeletedEvent;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;
import com.mindconnect.domain.phonecontact.port.repository.PhoneContactRepository;

public class DeletePhoneContactUseCase {

    private final PhoneContactRepository repository;

    public DeletePhoneContactUseCase(PhoneContactRepository repository) {
        this.repository = repository;
    }

    public PhoneContactDeletedEvent execute(PhoneContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PhoneContactNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new PhoneContactDeletedEvent(id, LocalDateTime.now());
    }
}
