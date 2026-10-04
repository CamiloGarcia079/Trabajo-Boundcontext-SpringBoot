package com.mindconnect.application.chatairun.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.mindconnect.domain.chatairun.event.ChatAiRunDeletedEvent;
import com.mindconnect.domain.chatairun.model.aggregate.ChatAiRun;
import com.mindconnect.domain.chatairun.model.valueobject.ChatAiRunId;
import com.mindconnect.domain.chatairun.port.repository.ChatAiRunRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatAiRunUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatAiRun aggregate = ChatAiRun.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        FakeChatAiRunRepository repository = new FakeChatAiRunRepository();
        repository.save(aggregate);
        DeleteChatAiRunUseCase useCase = new DeleteChatAiRunUseCase(repository);

        ChatAiRunDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatAiRunRepository repository = new FakeChatAiRunRepository();
        DeleteChatAiRunUseCase useCase = new DeleteChatAiRunUseCase(repository);

        assertThrows(ChatAiRunNotFoundApplicationException.class, () -> useCase.execute(ChatAiRunId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatAiRunRepository implements ChatAiRunRepository {
        private final Map<ChatAiRunId, ChatAiRun> store = new LinkedHashMap<>();
        private ChatAiRun deleted;

        @Override
        public ChatAiRun save(ChatAiRun aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatAiRun> findById(ChatAiRunId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatAiRun> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatAiRun aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
