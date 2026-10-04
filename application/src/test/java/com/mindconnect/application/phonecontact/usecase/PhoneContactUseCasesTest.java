package com.mindconnect.application.phonecontact.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.phonecontact.command.RegisterPhoneContactCommand;
import com.mindconnect.application.phonecontact.command.UpdatePhoneContactCommand;
import com.mindconnect.application.phonecontact.dto.PhoneContactResponse;
import com.mindconnect.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import com.mindconnect.domain.phonecontact.model.aggregate.PhoneContact;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;
import com.mindconnect.domain.phonecontact.port.repository.PhoneContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneContactUseCasesTest {

    private FakePhoneContactRepository repository;
    private RegisterPhoneContactUseCase register;
    private GetPhoneContactByIdUseCase getById;
    private ListPhoneContactUseCase list;
    private UpdatePhoneContactUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakePhoneContactRepository();
        register = new RegisterPhoneContactUseCase(repository);
        getById = new GetPhoneContactByIdUseCase(repository);
        list = new ListPhoneContactUseCase(repository);
        update = new UpdatePhoneContactUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        PhoneContactResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        PhoneContactResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new PhoneContactId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(PhoneContactNotFoundApplicationException.class, () -> getById.execute(PhoneContactId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        PhoneContactResponse created = register.execute(commandA());

        PhoneContactResponse updated = update.execute(updateCommand(new PhoneContactId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new PhoneContactId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(PhoneContactNotFoundApplicationException.class, () -> update.execute(updateCommand(PhoneContactId.generate())));
    }

    private RegisterPhoneContactCommand commandA() {
        return new RegisterPhoneContactCommand(UUID.randomUUID(), "valor-a", "valor-a");
    }

    private UpdatePhoneContactCommand updateCommand(PhoneContactId id) {
        return new UpdatePhoneContactCommand(id, UUID.randomUUID(), "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePhoneContactRepository implements PhoneContactRepository {
        private final Map<PhoneContactId, PhoneContact> store = new LinkedHashMap<>();

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
            store.remove(aggregate.id());
        }
    }
}
