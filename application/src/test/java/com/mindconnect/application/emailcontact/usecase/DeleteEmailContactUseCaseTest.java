package com.mindconnect.application.emailcontact.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import com.mindconnect.domain.emailcontact.event.EmailContactDeletedEvent;
import com.mindconnect.domain.emailcontact.model.aggregate.EmailContact;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;
import com.mindconnect.domain.emailcontact.port.repository.EmailContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteEmailContactUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        EmailContact aggregate = EmailContact.register(UUID.randomUUID(), "valor-a", "valor-a");
        FakeEmailContactRepository repository = new FakeEmailContactRepository();
        repository.save(aggregate);
        DeleteEmailContactUseCase useCase = new DeleteEmailContactUseCase(repository);

        EmailContactDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeEmailContactRepository repository = new FakeEmailContactRepository();
        DeleteEmailContactUseCase useCase = new DeleteEmailContactUseCase(repository);

        assertThrows(EmailContactNotFoundApplicationException.class, () -> useCase.execute(EmailContactId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEmailContactRepository implements EmailContactRepository {
        private final Map<EmailContactId, EmailContact> store = new LinkedHashMap<>();
        private EmailContact deleted;

        @Override
        public EmailContact save(EmailContact aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<EmailContact> findById(EmailContactId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EmailContact> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(EmailContact aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
