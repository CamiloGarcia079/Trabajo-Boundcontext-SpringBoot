package com.mindconnect.application.chatairunerror.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import com.mindconnect.domain.chatairunerror.event.ChatAiRunErrorDeletedEvent;
import com.mindconnect.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.mindconnect.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatAiRunErrorUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatAiRunError aggregate = ChatAiRunError.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a");
        FakeChatAiRunErrorRepository repository = new FakeChatAiRunErrorRepository();
        repository.save(aggregate);
        DeleteChatAiRunErrorUseCase useCase = new DeleteChatAiRunErrorUseCase(repository);

        ChatAiRunErrorDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatAiRunErrorRepository repository = new FakeChatAiRunErrorRepository();
        DeleteChatAiRunErrorUseCase useCase = new DeleteChatAiRunErrorUseCase(repository);

        assertThrows(ChatAiRunErrorNotFoundApplicationException.class, () -> useCase.execute(ChatAiRunErrorId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatAiRunErrorRepository implements ChatAiRunErrorRepository {
        private final Map<ChatAiRunErrorId, ChatAiRunError> store = new LinkedHashMap<>();
        private ChatAiRunError deleted;

        @Override
        public ChatAiRunError save(ChatAiRunError aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatAiRunError> findById(ChatAiRunErrorId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatAiRunError> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatAiRunError aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
