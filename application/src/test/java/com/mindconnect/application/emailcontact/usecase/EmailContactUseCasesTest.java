package com.mindconnect.application.emailcontact.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.emailcontact.command.RegisterEmailContactCommand;
import com.mindconnect.application.emailcontact.command.UpdateEmailContactCommand;
import com.mindconnect.application.emailcontact.dto.EmailContactResponse;
import com.mindconnect.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import com.mindconnect.domain.emailcontact.model.aggregate.EmailContact;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;
import com.mindconnect.domain.emailcontact.port.repository.EmailContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailContactUseCasesTest {

    private FakeEmailContactRepository repository;
    private RegisterEmailContactUseCase register;
    private GetEmailContactByIdUseCase getById;
    private ListEmailContactUseCase list;
    private UpdateEmailContactUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeEmailContactRepository();
        register = new RegisterEmailContactUseCase(repository);
        getById = new GetEmailContactByIdUseCase(repository);
        list = new ListEmailContactUseCase(repository);
        update = new UpdateEmailContactUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        EmailContactResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        EmailContactResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new EmailContactId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EmailContactNotFoundApplicationException.class, () -> getById.execute(EmailContactId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        EmailContactResponse created = register.execute(commandA());

        EmailContactResponse updated = update.execute(updateCommand(new EmailContactId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new EmailContactId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EmailContactNotFoundApplicationException.class, () -> update.execute(updateCommand(EmailContactId.generate())));
    }

    private RegisterEmailContactCommand commandA() {
        return new RegisterEmailContactCommand(UUID.randomUUID(), "valor-a", "valor-a");
    }

    private UpdateEmailContactCommand updateCommand(EmailContactId id) {
        return new UpdateEmailContactCommand(id, UUID.randomUUID(), "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEmailContactRepository implements EmailContactRepository {
        private final Map<EmailContactId, EmailContact> store = new LinkedHashMap<>();

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
            store.remove(aggregate.id());
        }
    }
}
