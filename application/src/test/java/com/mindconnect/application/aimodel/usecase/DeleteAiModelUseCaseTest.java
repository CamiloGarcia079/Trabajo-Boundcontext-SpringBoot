package com.mindconnect.application.aimodel.usecase;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.mindconnect.domain.aimodel.event.AiModelDeletedEvent;
import com.mindconnect.domain.aimodel.model.aggregate.AiModel;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;
import com.mindconnect.domain.aimodel.port.repository.AiModelRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteAiModelUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        AiModel aggregate = AiModel.register(UUID.randomUUID(), "valor-a", "valor-a", BigDecimal.ONE, BigDecimal.ONE, 1, 1);
        FakeAiModelRepository repository = new FakeAiModelRepository();
        repository.save(aggregate);
        DeleteAiModelUseCase useCase = new DeleteAiModelUseCase(repository);

        AiModelDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeAiModelRepository repository = new FakeAiModelRepository();
        DeleteAiModelUseCase useCase = new DeleteAiModelUseCase(repository);

        assertThrows(AiModelNotFoundApplicationException.class, () -> useCase.execute(AiModelId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeAiModelRepository implements AiModelRepository {
        private final Map<AiModelId, AiModel> store = new LinkedHashMap<>();
        private AiModel deleted;

        @Override
        public AiModel save(AiModel aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<AiModel> findById(AiModelId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<AiModel> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(AiModel aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
