package com.mindconnect.application.chatconversation.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.mindconnect.domain.chatconversation.event.ChatConversationDeletedEvent;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;
import com.mindconnect.domain.chatconversation.port.repository.ChatConversationRepository;

public class DeleteChatConversationUseCase {

    private final ChatConversationRepository repository;

    public DeleteChatConversationUseCase(ChatConversationRepository repository) {
        this.repository = repository;
    }

    public ChatConversationDeletedEvent execute(ChatConversationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatConversationNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatConversationDeletedEvent(id, LocalDateTime.now());
    }
}
