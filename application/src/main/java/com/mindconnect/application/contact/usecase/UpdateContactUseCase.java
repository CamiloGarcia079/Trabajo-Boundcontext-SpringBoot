package com.mindconnect.application.contact.usecase;

import com.mindconnect.application.contact.command.UpdateContactCommand;
import com.mindconnect.application.contact.dto.ContactResponse;
import com.mindconnect.application.contact.exception.ContactNotFoundApplicationException;
import com.mindconnect.domain.contact.model.aggregate.Contact;
import com.mindconnect.domain.contact.port.repository.ContactRepository;

public class UpdateContactUseCase {

    private final ContactRepository repository;

    public UpdateContactUseCase(ContactRepository repository) {
        this.repository = repository;
    }

    public ContactResponse execute(UpdateContactCommand command) {
        Contact aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ContactNotFoundApplicationException(command.id()));
        aggregate.update(
                command.fullName(), command.email(), command.notes(), command.cityId(), command.updatedBy());
        Contact saved = repository.save(aggregate);
        return ContactResponse.fromDomain(saved);
    }
}
