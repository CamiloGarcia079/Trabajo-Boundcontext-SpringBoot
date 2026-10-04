package com.mindconnect.application.contact.usecase;

import com.mindconnect.application.contact.command.RegisterContactCommand;
import com.mindconnect.application.contact.dto.ContactResponse;
import com.mindconnect.domain.contact.model.aggregate.Contact;
import com.mindconnect.domain.contact.port.repository.ContactRepository;

public class RegisterContactUseCase {

    private final ContactRepository repository;

    public RegisterContactUseCase(ContactRepository repository) {
        this.repository = repository;
    }

    public ContactResponse execute(RegisterContactCommand command) {
        Contact aggregate = Contact.register(
                command.fullName(), command.email(), command.notes(), command.cityId(), command.createdBy(), command.updatedBy());
        Contact saved = repository.save(aggregate);
        return ContactResponse.fromDomain(saved);
    }
}
