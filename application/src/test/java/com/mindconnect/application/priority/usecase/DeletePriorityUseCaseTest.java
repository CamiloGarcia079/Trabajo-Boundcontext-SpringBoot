package com.mindconnect.application.priority.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.priority.exception.PriorityNotFoundApplicationException;
import com.mindconnect.domain.priority.event.PriorityDeletedEvent;
import com.mindconnect.domain.priority.model.aggregate.Priority;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;
import com.mindconnect.domain.priority.port.repository.PriorityRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeletePriorityUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        Priority aggregate = Priority.register("valor-a");
        FakePriorityRepository repository = new FakePriorityRepository();
        repository.save(aggregate);
        DeletePriorityUseCase useCase = new DeletePriorityUseCase(repository);

        PriorityDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakePriorityRepository repository = new FakePriorityRepository();
        DeletePriorityUseCase useCase = new DeletePriorityUseCase(repository);

        assertThrows(PriorityNotFoundApplicationException.class, () -> useCase.execute(PriorityId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePriorityRepository implements PriorityRepository {
        private final Map<PriorityId, Priority> store = new LinkedHashMap<>();
        private Priority deleted;

        @Override
        public Priority save(Priority aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Priority> findById(PriorityId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Priority> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Priority aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
