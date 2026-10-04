package com.mindconnect.application.contact.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.contact.command.RegisterContactCommand;
import com.mindconnect.application.contact.command.UpdateContactCommand;
import com.mindconnect.application.contact.dto.ContactResponse;
import com.mindconnect.application.contact.exception.ContactNotFoundApplicationException;
import com.mindconnect.domain.contact.model.aggregate.Contact;
import com.mindconnect.domain.contact.model.valueobject.ContactId;
import com.mindconnect.domain.contact.port.repository.ContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContactUseCasesTest {

    private FakeContactRepository repository;
    private RegisterContactUseCase register;
    private GetContactByIdUseCase getById;
    private ListContactUseCase list;
    private UpdateContactUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeContactRepository();
        register = new RegisterContactUseCase(repository);
        getById = new GetContactByIdUseCase(repository);
        list = new ListContactUseCase(repository);
        update = new UpdateContactUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ContactResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ContactResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ContactId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ContactNotFoundApplicationException.class, () -> getById.execute(ContactId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ContactResponse created = register.execute(commandA());

        ContactResponse updated = update.execute(updateCommand(new ContactId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ContactId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ContactNotFoundApplicationException.class, () -> update.execute(updateCommand(ContactId.generate())));
    }

    private RegisterContactCommand commandA() {
        return new RegisterContactCommand("valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    private UpdateContactCommand updateCommand(ContactId id) {
        return new UpdateContactCommand(id, "valor-b", "valor-b", "valor-b", UUID.randomUUID(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeContactRepository implements ContactRepository {
        private final Map<ContactId, Contact> store = new LinkedHashMap<>();

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
            store.remove(aggregate.id());
        }
    }
}
