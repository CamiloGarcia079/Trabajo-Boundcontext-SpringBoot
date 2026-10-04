package com.mindconnect.application.stateregion.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.mindconnect.domain.stateregion.event.StateRegionDeletedEvent;
import com.mindconnect.domain.stateregion.model.aggregate.StateRegion;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;
import com.mindconnect.domain.stateregion.port.repository.StateRegionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteStateRegionUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        StateRegion aggregate = StateRegion.register("valor-a", "valor-a", "valor-a", UUID.randomUUID());
        FakeStateRegionRepository repository = new FakeStateRegionRepository();
        repository.save(aggregate);
        DeleteStateRegionUseCase useCase = new DeleteStateRegionUseCase(repository);

        StateRegionDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeStateRegionRepository repository = new FakeStateRegionRepository();
        DeleteStateRegionUseCase useCase = new DeleteStateRegionUseCase(repository);

        assertThrows(StateRegionNotFoundApplicationException.class, () -> useCase.execute(StateRegionId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeStateRegionRepository implements StateRegionRepository {
        private final Map<StateRegionId, StateRegion> store = new LinkedHashMap<>();
        private StateRegion deleted;

        @Override
        public StateRegion save(StateRegion aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<StateRegion> findById(StateRegionId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<StateRegion> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(StateRegion aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
