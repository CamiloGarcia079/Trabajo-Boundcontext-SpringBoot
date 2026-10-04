package com.mindconnect.application.consenttype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import com.mindconnect.domain.consenttype.event.ConsentTypeDeletedEvent;
import com.mindconnect.domain.consenttype.model.aggregate.ConsentType;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;
import com.mindconnect.domain.consenttype.port.repository.ConsentTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteConsentTypeUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ConsentType aggregate = ConsentType.register("valor-a", "valor-a", "valor-a");
        FakeConsentTypeRepository repository = new FakeConsentTypeRepository();
        repository.save(aggregate);
        DeleteConsentTypeUseCase useCase = new DeleteConsentTypeUseCase(repository);

        ConsentTypeDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeConsentTypeRepository repository = new FakeConsentTypeRepository();
        DeleteConsentTypeUseCase useCase = new DeleteConsentTypeUseCase(repository);

        assertThrows(ConsentTypeNotFoundApplicationException.class, () -> useCase.execute(ConsentTypeId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeConsentTypeRepository implements ConsentTypeRepository {
        private final Map<ConsentTypeId, ConsentType> store = new LinkedHashMap<>();
        private ConsentType deleted;

        @Override
        public ConsentType save(ConsentType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ConsentType> findById(ConsentTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ConsentType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ConsentType aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
