package com.mindconnect.application.chatparticipant.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import com.mindconnect.domain.chatparticipant.event.ChatParticipantDeletedEvent;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.mindconnect.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class DeleteChatParticipantUseCase {

    private final ChatParticipantRepository repository;

    public DeleteChatParticipantUseCase(ChatParticipantRepository repository) {
        this.repository = repository;
    }

    public ChatParticipantDeletedEvent execute(ChatParticipantId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatParticipantNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatParticipantDeletedEvent(id, LocalDateTime.now());
    }
}
