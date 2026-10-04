package com.mindconnect.application.emailcontact.usecase;

import com.mindconnect.application.emailcontact.command.RegisterEmailContactCommand;
import com.mindconnect.application.emailcontact.dto.EmailContactResponse;
import com.mindconnect.domain.emailcontact.model.aggregate.EmailContact;
import com.mindconnect.domain.emailcontact.port.repository.EmailContactRepository;

public class RegisterEmailContactUseCase {

    private final EmailContactRepository repository;

    public RegisterEmailContactUseCase(EmailContactRepository repository) {
        this.repository = repository;
    }

    public EmailContactResponse execute(RegisterEmailContactCommand command) {
        EmailContact aggregate = EmailContact.register(
                command.contactId(), command.email(), command.notes());
        EmailContact saved = repository.save(aggregate);
        return EmailContactResponse.fromDomain(saved);
    }
}
