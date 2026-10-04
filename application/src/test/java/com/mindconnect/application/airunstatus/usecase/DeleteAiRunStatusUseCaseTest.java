package com.mindconnect.application.airunstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.mindconnect.domain.airunstatus.event.AiRunStatusDeletedEvent;
import com.mindconnect.domain.airunstatus.model.aggregate.AiRunStatus;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.mindconnect.domain.airunstatus.port.repository.AiRunStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteAiRunStatusUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        AiRunStatus aggregate = AiRunStatus.register("valor-a");
        FakeAiRunStatusRepository repository = new FakeAiRunStatusRepository();
        repository.save(aggregate);
        DeleteAiRunStatusUseCase useCase = new DeleteAiRunStatusUseCase(repository);

        AiRunStatusDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeAiRunStatusRepository repository = new FakeAiRunStatusRepository();
        DeleteAiRunStatusUseCase useCase = new DeleteAiRunStatusUseCase(repository);

        assertThrows(AiRunStatusNotFoundApplicationException.class, () -> useCase.execute(AiRunStatusId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeAiRunStatusRepository implements AiRunStatusRepository {
        private final Map<AiRunStatusId, AiRunStatus> store = new LinkedHashMap<>();
        private AiRunStatus deleted;

        @Override
        public AiRunStatus save(AiRunStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<AiRunStatus> findById(AiRunStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<AiRunStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(AiRunStatus aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
