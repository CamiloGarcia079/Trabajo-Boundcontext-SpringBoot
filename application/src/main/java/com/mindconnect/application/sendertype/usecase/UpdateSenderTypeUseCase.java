package com.mindconnect.application.sendertype.usecase;

import com.mindconnect.application.sendertype.command.UpdateSenderTypeCommand;
import com.mindconnect.application.sendertype.dto.SenderTypeResponse;
import com.mindconnect.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.mindconnect.domain.sendertype.model.aggregate.SenderType;
import com.mindconnect.domain.sendertype.port.repository.SenderTypeRepository;

public class UpdateSenderTypeUseCase {

    private final SenderTypeRepository repository;

    public UpdateSenderTypeUseCase(SenderTypeRepository repository) {
        this.repository = repository;
    }

    public SenderTypeResponse execute(UpdateSenderTypeCommand command) {
        SenderType aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SenderTypeNotFoundApplicationException(command.id()));
        aggregate.update(
                command.nameType());
        SenderType saved = repository.save(aggregate);
        return SenderTypeResponse.fromDomain(saved);
    }
}
