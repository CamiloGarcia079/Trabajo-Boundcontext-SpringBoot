package com.mindconnect.application.chatmessage.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.mindconnect.domain.chatmessage.event.ChatMessageDeletedEvent;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;
import com.mindconnect.domain.chatmessage.port.repository.ChatMessageRepository;

public class DeleteChatMessageUseCase {

    private final ChatMessageRepository repository;

    public DeleteChatMessageUseCase(ChatMessageRepository repository) {
        this.repository = repository;
    }

    public ChatMessageDeletedEvent execute(ChatMessageId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatMessageNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatMessageDeletedEvent(id, LocalDateTime.now());
    }
}
