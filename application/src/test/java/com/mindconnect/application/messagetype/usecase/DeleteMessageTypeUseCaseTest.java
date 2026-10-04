package com.mindconnect.application.messagetype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.mindconnect.domain.messagetype.event.MessageTypeDeletedEvent;
import com.mindconnect.domain.messagetype.model.aggregate.MessageType;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;
import com.mindconnect.domain.messagetype.port.repository.MessageTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteMessageTypeUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        MessageType aggregate = MessageType.register("valor-a");
        FakeMessageTypeRepository repository = new FakeMessageTypeRepository();
        repository.save(aggregate);
        DeleteMessageTypeUseCase useCase = new DeleteMessageTypeUseCase(repository);

        MessageTypeDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeMessageTypeRepository repository = new FakeMessageTypeRepository();
        DeleteMessageTypeUseCase useCase = new DeleteMessageTypeUseCase(repository);

        assertThrows(MessageTypeNotFoundApplicationException.class, () -> useCase.execute(MessageTypeId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeMessageTypeRepository implements MessageTypeRepository {
        private final Map<MessageTypeId, MessageType> store = new LinkedHashMap<>();
        private MessageType deleted;

        @Override
        public MessageType save(MessageType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<MessageType> findById(MessageTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<MessageType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(MessageType aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
