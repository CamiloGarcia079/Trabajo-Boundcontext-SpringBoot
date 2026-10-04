package com.mindconnect.application.professional.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.mindconnect.domain.professional.event.ProfessionalDeletedEvent;
import com.mindconnect.domain.professional.model.aggregate.Professional;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;
import com.mindconnect.domain.professional.port.repository.ProfessionalRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteProfessionalUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        Professional aggregate = Professional.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), "valor-a", UUID.randomUUID());
        FakeProfessionalRepository repository = new FakeProfessionalRepository();
        repository.save(aggregate);
        DeleteProfessionalUseCase useCase = new DeleteProfessionalUseCase(repository);

        ProfessionalDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeProfessionalRepository repository = new FakeProfessionalRepository();
        DeleteProfessionalUseCase useCase = new DeleteProfessionalUseCase(repository);

        assertThrows(ProfessionalNotFoundApplicationException.class, () -> useCase.execute(ProfessionalId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeProfessionalRepository implements ProfessionalRepository {
        private final Map<ProfessionalId, Professional> store = new LinkedHashMap<>();
        private Professional deleted;

        @Override
        public Professional save(Professional aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Professional> findById(ProfessionalId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Professional> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Professional aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
