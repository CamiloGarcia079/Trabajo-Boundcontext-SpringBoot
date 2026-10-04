package com.mindconnect.application.conversationstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.mindconnect.domain.conversationstatus.event.ConversationStatusDeletedEvent;
import com.mindconnect.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.mindconnect.domain.conversationstatus.port.repository.ConversationStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteConversationStatusUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ConversationStatus aggregate = ConversationStatus.register("valor-a");
        FakeConversationStatusRepository repository = new FakeConversationStatusRepository();
        repository.save(aggregate);
        DeleteConversationStatusUseCase useCase = new DeleteConversationStatusUseCase(repository);

        ConversationStatusDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeConversationStatusRepository repository = new FakeConversationStatusRepository();
        DeleteConversationStatusUseCase useCase = new DeleteConversationStatusUseCase(repository);

        assertThrows(ConversationStatusNotFoundApplicationException.class, () -> useCase.execute(ConversationStatusId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeConversationStatusRepository implements ConversationStatusRepository {
        private final Map<ConversationStatusId, ConversationStatus> store = new LinkedHashMap<>();
        private ConversationStatus deleted;

        @Override
        public ConversationStatus save(ConversationStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ConversationStatus> findById(ConversationStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ConversationStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ConversationStatus aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
