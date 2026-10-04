package com.mindconnect.application.sendertype.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.mindconnect.domain.sendertype.event.SenderTypeDeletedEvent;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;
import com.mindconnect.domain.sendertype.port.repository.SenderTypeRepository;

public class DeleteSenderTypeUseCase {

    private final SenderTypeRepository repository;

    public DeleteSenderTypeUseCase(SenderTypeRepository repository) {
        this.repository = repository;
    }

    public SenderTypeDeletedEvent execute(SenderTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SenderTypeNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new SenderTypeDeletedEvent(id, LocalDateTime.now());
    }
}
