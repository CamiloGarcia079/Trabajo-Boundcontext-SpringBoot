package com.mindconnect.application.chatparticipant.usecase;

import com.mindconnect.application.chatparticipant.dto.ChatParticipantResponse;
import com.mindconnect.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.mindconnect.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class GetChatParticipantByIdUseCase {

    private final ChatParticipantRepository repository;

    public GetChatParticipantByIdUseCase(ChatParticipantRepository repository) {
        this.repository = repository;
    }

    public ChatParticipantResponse execute(ChatParticipantId id) {
        return repository.findById(id)
                .map(ChatParticipantResponse::fromDomain)
                .orElseThrow(() -> new ChatParticipantNotFoundApplicationException(id));
    }
}
