package com.mindconnect.application.chatescalationstatushistory.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import com.mindconnect.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryDeletedEvent;
import com.mindconnect.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.mindconnect.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatEscalationStatusHistoryUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatEscalationStatusHistory aggregate = ChatEscalationStatusHistory.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
        FakeChatEscalationStatusHistoryRepository repository = new FakeChatEscalationStatusHistoryRepository();
        repository.save(aggregate);
        DeleteChatEscalationStatusHistoryUseCase useCase = new DeleteChatEscalationStatusHistoryUseCase(repository);

        ChatEscalationStatusHistoryDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatEscalationStatusHistoryRepository repository = new FakeChatEscalationStatusHistoryRepository();
        DeleteChatEscalationStatusHistoryUseCase useCase = new DeleteChatEscalationStatusHistoryUseCase(repository);

        assertThrows(ChatEscalationStatusHistoryNotFoundApplicationException.class, () -> useCase.execute(ChatEscalationStatusHistoryId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatEscalationStatusHistoryRepository implements ChatEscalationStatusHistoryRepository {
        private final Map<ChatEscalationStatusHistoryId, ChatEscalationStatusHistory> store = new LinkedHashMap<>();
        private ChatEscalationStatusHistory deleted;

        @Override
        public ChatEscalationStatusHistory save(ChatEscalationStatusHistory aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatEscalationStatusHistory> findById(ChatEscalationStatusHistoryId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatEscalationStatusHistory> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatEscalationStatusHistory aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
