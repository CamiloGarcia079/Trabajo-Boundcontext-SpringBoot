package com.mindconnect.application.messagetype.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.mindconnect.domain.messagetype.event.MessageTypeDeletedEvent;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;
import com.mindconnect.domain.messagetype.port.repository.MessageTypeRepository;

public class DeleteMessageTypeUseCase {

    private final MessageTypeRepository repository;

    public DeleteMessageTypeUseCase(MessageTypeRepository repository) {
        this.repository = repository;
    }

    public MessageTypeDeletedEvent execute(MessageTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MessageTypeNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new MessageTypeDeletedEvent(id, LocalDateTime.now());
    }
}
