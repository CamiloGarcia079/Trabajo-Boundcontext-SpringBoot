package com.mindconnect.application.treatmentstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentstatus.event.TreatmentStatusDeletedEvent;
import com.mindconnect.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.mindconnect.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteTreatmentStatusUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        TreatmentStatus aggregate = TreatmentStatus.register("valor-a", "valor-a");
        FakeTreatmentStatusRepository repository = new FakeTreatmentStatusRepository();
        repository.save(aggregate);
        DeleteTreatmentStatusUseCase useCase = new DeleteTreatmentStatusUseCase(repository);

        TreatmentStatusDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeTreatmentStatusRepository repository = new FakeTreatmentStatusRepository();
        DeleteTreatmentStatusUseCase useCase = new DeleteTreatmentStatusUseCase(repository);

        assertThrows(TreatmentStatusNotFoundApplicationException.class, () -> useCase.execute(TreatmentStatusId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeTreatmentStatusRepository implements TreatmentStatusRepository {
        private final Map<TreatmentStatusId, TreatmentStatus> store = new LinkedHashMap<>();
        private TreatmentStatus deleted;

        @Override
        public TreatmentStatus save(TreatmentStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<TreatmentStatus> findById(TreatmentStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(TreatmentStatus aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
