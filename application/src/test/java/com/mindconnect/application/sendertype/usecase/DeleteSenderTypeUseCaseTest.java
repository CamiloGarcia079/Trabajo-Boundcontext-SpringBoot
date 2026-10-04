package com.mindconnect.application.sendertype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.mindconnect.domain.sendertype.event.SenderTypeDeletedEvent;
import com.mindconnect.domain.sendertype.model.aggregate.SenderType;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;
import com.mindconnect.domain.sendertype.port.repository.SenderTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteSenderTypeUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        SenderType aggregate = SenderType.register("valor-a");
        FakeSenderTypeRepository repository = new FakeSenderTypeRepository();
        repository.save(aggregate);
        DeleteSenderTypeUseCase useCase = new DeleteSenderTypeUseCase(repository);

        SenderTypeDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeSenderTypeRepository repository = new FakeSenderTypeRepository();
        DeleteSenderTypeUseCase useCase = new DeleteSenderTypeUseCase(repository);

        assertThrows(SenderTypeNotFoundApplicationException.class, () -> useCase.execute(SenderTypeId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeSenderTypeRepository implements SenderTypeRepository {
        private final Map<SenderTypeId, SenderType> store = new LinkedHashMap<>();
        private SenderType deleted;

        @Override
        public SenderType save(SenderType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<SenderType> findById(SenderTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<SenderType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(SenderType aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
