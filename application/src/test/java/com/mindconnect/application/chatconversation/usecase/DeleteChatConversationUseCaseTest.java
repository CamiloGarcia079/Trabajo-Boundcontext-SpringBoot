package com.mindconnect.application.chatconversation.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.mindconnect.domain.chatconversation.event.ChatConversationDeletedEvent;
import com.mindconnect.domain.chatconversation.model.aggregate.ChatConversation;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;
import com.mindconnect.domain.chatconversation.port.repository.ChatConversationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatConversationUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatConversation aggregate = ChatConversation.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), true, LocalDateTime.now(), UUID.randomUUID());
        FakeChatConversationRepository repository = new FakeChatConversationRepository();
        repository.save(aggregate);
        DeleteChatConversationUseCase useCase = new DeleteChatConversationUseCase(repository);

        ChatConversationDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatConversationRepository repository = new FakeChatConversationRepository();
        DeleteChatConversationUseCase useCase = new DeleteChatConversationUseCase(repository);

        assertThrows(ChatConversationNotFoundApplicationException.class, () -> useCase.execute(ChatConversationId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatConversationRepository implements ChatConversationRepository {
        private final Map<ChatConversationId, ChatConversation> store = new LinkedHashMap<>();
        private ChatConversation deleted;

        @Override
        public ChatConversation save(ChatConversation aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatConversation> findById(ChatConversationId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatConversation> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatConversation aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
