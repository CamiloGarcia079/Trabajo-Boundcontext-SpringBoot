package com.mindconnect.application.phonecontact.usecase;

import com.mindconnect.application.phonecontact.command.UpdatePhoneContactCommand;
import com.mindconnect.application.phonecontact.dto.PhoneContactResponse;
import com.mindconnect.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import com.mindconnect.domain.phonecontact.model.aggregate.PhoneContact;
import com.mindconnect.domain.phonecontact.port.repository.PhoneContactRepository;

public class UpdatePhoneContactUseCase {

    private final PhoneContactRepository repository;

    public UpdatePhoneContactUseCase(PhoneContactRepository repository) {
        this.repository = repository;
    }

    public PhoneContactResponse execute(UpdatePhoneContactCommand command) {
        PhoneContact aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PhoneContactNotFoundApplicationException(command.id()));
        aggregate.update(
                command.contactId(), command.phone(), command.notes());
        PhoneContact saved = repository.save(aggregate);
        return PhoneContactResponse.fromDomain(saved);
    }
}
