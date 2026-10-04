package com.mindconnect.application.consenttype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.consenttype.command.RegisterConsentTypeCommand;
import com.mindconnect.application.consenttype.command.UpdateConsentTypeCommand;
import com.mindconnect.application.consenttype.dto.ConsentTypeResponse;
import com.mindconnect.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import com.mindconnect.domain.consenttype.model.aggregate.ConsentType;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;
import com.mindconnect.domain.consenttype.port.repository.ConsentTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsentTypeUseCasesTest {

    private FakeConsentTypeRepository repository;
    private RegisterConsentTypeUseCase register;
    private GetConsentTypeByIdUseCase getById;
    private ListConsentTypeUseCase list;
    private UpdateConsentTypeUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeConsentTypeRepository();
        register = new RegisterConsentTypeUseCase(repository);
        getById = new GetConsentTypeByIdUseCase(repository);
        list = new ListConsentTypeUseCase(repository);
        update = new UpdateConsentTypeUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ConsentTypeResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ConsentTypeResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ConsentTypeId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConsentTypeNotFoundApplicationException.class, () -> getById.execute(ConsentTypeId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ConsentTypeResponse created = register.execute(commandA());

        ConsentTypeResponse updated = update.execute(updateCommand(new ConsentTypeId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ConsentTypeId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConsentTypeNotFoundApplicationException.class, () -> update.execute(updateCommand(ConsentTypeId.generate())));
    }

    private RegisterConsentTypeCommand commandA() {
        return new RegisterConsentTypeCommand("valor-a", "valor-a", "valor-a");
    }

    private UpdateConsentTypeCommand updateCommand(ConsentTypeId id) {
        return new UpdateConsentTypeCommand(id, "valor-b", "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeConsentTypeRepository implements ConsentTypeRepository {
        private final Map<ConsentTypeId, ConsentType> store = new LinkedHashMap<>();

        @Override
        public ConsentType save(ConsentType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ConsentType> findById(ConsentTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ConsentType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ConsentType aggregate) {
            store.remove(aggregate.id());
        }
    }
}
