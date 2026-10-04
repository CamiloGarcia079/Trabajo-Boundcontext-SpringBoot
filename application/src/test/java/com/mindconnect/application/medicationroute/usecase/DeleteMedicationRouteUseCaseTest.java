package com.mindconnect.application.medicationroute.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import com.mindconnect.domain.medicationroute.event.MedicationRouteDeletedEvent;
import com.mindconnect.domain.medicationroute.model.aggregate.MedicationRoute;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.mindconnect.domain.medicationroute.port.repository.MedicationRouteRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteMedicationRouteUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        MedicationRoute aggregate = MedicationRoute.register("valor-a", "valor-a");
        FakeMedicationRouteRepository repository = new FakeMedicationRouteRepository();
        repository.save(aggregate);
        DeleteMedicationRouteUseCase useCase = new DeleteMedicationRouteUseCase(repository);

        MedicationRouteDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeMedicationRouteRepository repository = new FakeMedicationRouteRepository();
        DeleteMedicationRouteUseCase useCase = new DeleteMedicationRouteUseCase(repository);

        assertThrows(MedicationRouteNotFoundApplicationException.class, () -> useCase.execute(MedicationRouteId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeMedicationRouteRepository implements MedicationRouteRepository {
        private final Map<MedicationRouteId, MedicationRoute> store = new LinkedHashMap<>();
        private MedicationRoute deleted;

        @Override
        public MedicationRoute save(MedicationRoute aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<MedicationRoute> findById(MedicationRouteId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<MedicationRoute> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(MedicationRoute aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
