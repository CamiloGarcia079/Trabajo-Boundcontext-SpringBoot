package com.mindconnect.application.chatairunmetric.usecase;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import com.mindconnect.domain.chatairunmetric.event.ChatAiRunMetricDeletedEvent;
import com.mindconnect.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.mindconnect.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatAiRunMetricUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatAiRunMetric aggregate = ChatAiRunMetric.register(UUID.randomUUID(), 1, 1, 1, BigDecimal.ONE);
        FakeChatAiRunMetricRepository repository = new FakeChatAiRunMetricRepository();
        repository.save(aggregate);
        DeleteChatAiRunMetricUseCase useCase = new DeleteChatAiRunMetricUseCase(repository);

        ChatAiRunMetricDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatAiRunMetricRepository repository = new FakeChatAiRunMetricRepository();
        DeleteChatAiRunMetricUseCase useCase = new DeleteChatAiRunMetricUseCase(repository);

        assertThrows(ChatAiRunMetricNotFoundApplicationException.class, () -> useCase.execute(ChatAiRunMetricId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatAiRunMetricRepository implements ChatAiRunMetricRepository {
        private final Map<ChatAiRunMetricId, ChatAiRunMetric> store = new LinkedHashMap<>();
        private ChatAiRunMetric deleted;

        @Override
        public ChatAiRunMetric save(ChatAiRunMetric aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatAiRunMetric> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatAiRunMetric aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
