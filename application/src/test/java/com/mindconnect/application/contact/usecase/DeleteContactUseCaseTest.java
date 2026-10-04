package com.mindconnect.application.contact.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.contact.exception.ContactNotFoundApplicationException;
import com.mindconnect.domain.contact.event.ContactDeletedEvent;
import com.mindconnect.domain.contact.model.aggregate.Contact;
import com.mindconnect.domain.contact.model.valueobject.ContactId;
import com.mindconnect.domain.contact.port.repository.ContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteContactUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        Contact aggregate = Contact.register("valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        FakeContactRepository repository = new FakeContactRepository();
        repository.save(aggregate);
        DeleteContactUseCase useCase = new DeleteContactUseCase(repository);

        ContactDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeContactRepository repository = new FakeContactRepository();
        DeleteContactUseCase useCase = new DeleteContactUseCase(repository);

        assertThrows(ContactNotFoundApplicationException.class, () -> useCase.execute(ContactId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeContactRepository implements ContactRepository {
        private final Map<ContactId, Contact> store = new LinkedHashMap<>();
        private Contact deleted;

        @Override
        public Contact save(Contact aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Contact> findById(ContactId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Contact> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Contact aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
