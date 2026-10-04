package com.mindconnect.application.encountertype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import com.mindconnect.domain.encountertype.event.EncounterTypeDeletedEvent;
import com.mindconnect.domain.encountertype.model.aggregate.EncounterType;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;
import com.mindconnect.domain.encountertype.port.repository.EncounterTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteEncounterTypeUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        EncounterType aggregate = EncounterType.register("valor-a", "valor-a");
        FakeEncounterTypeRepository repository = new FakeEncounterTypeRepository();
        repository.save(aggregate);
        DeleteEncounterTypeUseCase useCase = new DeleteEncounterTypeUseCase(repository);

        EncounterTypeDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeEncounterTypeRepository repository = new FakeEncounterTypeRepository();
        DeleteEncounterTypeUseCase useCase = new DeleteEncounterTypeUseCase(repository);

        assertThrows(EncounterTypeNotFoundApplicationException.class, () -> useCase.execute(EncounterTypeId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEncounterTypeRepository implements EncounterTypeRepository {
        private final Map<EncounterTypeId, EncounterType> store = new LinkedHashMap<>();
        private EncounterType deleted;

        @Override
        public EncounterType save(EncounterType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<EncounterType> findById(EncounterTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EncounterType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(EncounterType aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
