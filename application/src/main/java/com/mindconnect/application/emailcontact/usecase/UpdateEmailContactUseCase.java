package com.mindconnect.application.emailcontact.usecase;

import com.mindconnect.application.emailcontact.command.UpdateEmailContactCommand;
import com.mindconnect.application.emailcontact.dto.EmailContactResponse;
import com.mindconnect.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import com.mindconnect.domain.emailcontact.model.aggregate.EmailContact;
import com.mindconnect.domain.emailcontact.port.repository.EmailContactRepository;

public class UpdateEmailContactUseCase {

    private final EmailContactRepository repository;

    public UpdateEmailContactUseCase(EmailContactRepository repository) {
        this.repository = repository;
    }

    public EmailContactResponse execute(UpdateEmailContactCommand command) {
        EmailContact aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EmailContactNotFoundApplicationException(command.id()));
        aggregate.update(
                command.contactId(), command.email(), command.notes());
        EmailContact saved = repository.save(aggregate);
        return EmailContactResponse.fromDomain(saved);
    }
}
