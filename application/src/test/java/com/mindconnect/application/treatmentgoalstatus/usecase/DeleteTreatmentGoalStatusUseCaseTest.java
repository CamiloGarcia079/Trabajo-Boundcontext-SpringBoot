package com.mindconnect.application.treatmentgoalstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoalstatus.event.TreatmentGoalStatusDeletedEvent;
import com.mindconnect.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.mindconnect.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteTreatmentGoalStatusUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        TreatmentGoalStatus aggregate = TreatmentGoalStatus.register("valor-a", "valor-a");
        FakeTreatmentGoalStatusRepository repository = new FakeTreatmentGoalStatusRepository();
        repository.save(aggregate);
        DeleteTreatmentGoalStatusUseCase useCase = new DeleteTreatmentGoalStatusUseCase(repository);

        TreatmentGoalStatusDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeTreatmentGoalStatusRepository repository = new FakeTreatmentGoalStatusRepository();
        DeleteTreatmentGoalStatusUseCase useCase = new DeleteTreatmentGoalStatusUseCase(repository);

        assertThrows(TreatmentGoalStatusNotFoundApplicationException.class, () -> useCase.execute(TreatmentGoalStatusId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeTreatmentGoalStatusRepository implements TreatmentGoalStatusRepository {
        private final Map<TreatmentGoalStatusId, TreatmentGoalStatus> store = new LinkedHashMap<>();
        private TreatmentGoalStatus deleted;

        @Override
        public TreatmentGoalStatus save(TreatmentGoalStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<TreatmentGoalStatus> findById(TreatmentGoalStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentGoalStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(TreatmentGoalStatus aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
