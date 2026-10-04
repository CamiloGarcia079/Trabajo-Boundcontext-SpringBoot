package com.mindconnect.application.providermodelai.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import com.mindconnect.domain.providermodelai.event.ProviderModelAiDeletedEvent;
import com.mindconnect.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.mindconnect.domain.providermodelai.port.repository.ProviderModelAiRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteProviderModelAiUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ProviderModelAi aggregate = ProviderModelAi.register("valor-a", "valor-a", "valor-a");
        FakeProviderModelAiRepository repository = new FakeProviderModelAiRepository();
        repository.save(aggregate);
        DeleteProviderModelAiUseCase useCase = new DeleteProviderModelAiUseCase(repository);

        ProviderModelAiDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeProviderModelAiRepository repository = new FakeProviderModelAiRepository();
        DeleteProviderModelAiUseCase useCase = new DeleteProviderModelAiUseCase(repository);

        assertThrows(ProviderModelAiNotFoundApplicationException.class, () -> useCase.execute(ProviderModelAiId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeProviderModelAiRepository implements ProviderModelAiRepository {
        private final Map<ProviderModelAiId, ProviderModelAi> store = new LinkedHashMap<>();
        private ProviderModelAi deleted;

        @Override
        public ProviderModelAi save(ProviderModelAi aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ProviderModelAi> findById(ProviderModelAiId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ProviderModelAi> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ProviderModelAi aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
