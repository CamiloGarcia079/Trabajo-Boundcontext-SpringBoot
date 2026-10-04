package com.mindconnect.application.phonecontact.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import com.mindconnect.domain.phonecontact.event.PhoneContactDeletedEvent;
import com.mindconnect.domain.phonecontact.model.aggregate.PhoneContact;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;
import com.mindconnect.domain.phonecontact.port.repository.PhoneContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeletePhoneContactUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        PhoneContact aggregate = PhoneContact.register(UUID.randomUUID(), "valor-a", "valor-a");
        FakePhoneContactRepository repository = new FakePhoneContactRepository();
        repository.save(aggregate);
        DeletePhoneContactUseCase useCase = new DeletePhoneContactUseCase(repository);

        PhoneContactDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakePhoneContactRepository repository = new FakePhoneContactRepository();
        DeletePhoneContactUseCase useCase = new DeletePhoneContactUseCase(repository);

        assertThrows(PhoneContactNotFoundApplicationException.class, () -> useCase.execute(PhoneContactId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePhoneContactRepository implements PhoneContactRepository {
        private final Map<PhoneContactId, PhoneContact> store = new LinkedHashMap<>();
        private PhoneContact deleted;

        @Override
        public PhoneContact save(PhoneContact aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<PhoneContact> findById(PhoneContactId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<PhoneContact> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(PhoneContact aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
