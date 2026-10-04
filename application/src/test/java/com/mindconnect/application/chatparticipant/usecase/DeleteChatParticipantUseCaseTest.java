package com.mindconnect.application.chatparticipant.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import com.mindconnect.domain.chatparticipant.event.ChatParticipantDeletedEvent;
import com.mindconnect.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.mindconnect.domain.chatparticipant.port.repository.ChatParticipantRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatParticipantUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatParticipant aggregate = ChatParticipant.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        FakeChatParticipantRepository repository = new FakeChatParticipantRepository();
        repository.save(aggregate);
        DeleteChatParticipantUseCase useCase = new DeleteChatParticipantUseCase(repository);

        ChatParticipantDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatParticipantRepository repository = new FakeChatParticipantRepository();
        DeleteChatParticipantUseCase useCase = new DeleteChatParticipantUseCase(repository);

        assertThrows(ChatParticipantNotFoundApplicationException.class, () -> useCase.execute(ChatParticipantId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatParticipantRepository implements ChatParticipantRepository {
        private final Map<ChatParticipantId, ChatParticipant> store = new LinkedHashMap<>();
        private ChatParticipant deleted;

        @Override
        public ChatParticipant save(ChatParticipant aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatParticipant> findById(ChatParticipantId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatParticipant> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatParticipant aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
