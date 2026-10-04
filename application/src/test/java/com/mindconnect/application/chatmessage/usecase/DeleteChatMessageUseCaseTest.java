package com.mindconnect.application.chatmessage.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.mindconnect.domain.chatmessage.event.ChatMessageDeletedEvent;
import com.mindconnect.domain.chatmessage.model.aggregate.ChatMessage;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;
import com.mindconnect.domain.chatmessage.port.repository.ChatMessageRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatMessageUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatMessage aggregate = ChatMessage.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a");
        FakeChatMessageRepository repository = new FakeChatMessageRepository();
        repository.save(aggregate);
        DeleteChatMessageUseCase useCase = new DeleteChatMessageUseCase(repository);

        ChatMessageDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatMessageRepository repository = new FakeChatMessageRepository();
        DeleteChatMessageUseCase useCase = new DeleteChatMessageUseCase(repository);

        assertThrows(ChatMessageNotFoundApplicationException.class, () -> useCase.execute(ChatMessageId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatMessageRepository implements ChatMessageRepository {
        private final Map<ChatMessageId, ChatMessage> store = new LinkedHashMap<>();
        private ChatMessage deleted;

        @Override
        public ChatMessage save(ChatMessage aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatMessage> findById(ChatMessageId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatMessage> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatMessage aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
