package com.mindconnect.application.providermodelai.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.providermodelai.command.RegisterProviderModelAiCommand;
import com.mindconnect.application.providermodelai.command.UpdateProviderModelAiCommand;
import com.mindconnect.application.providermodelai.dto.ProviderModelAiResponse;
import com.mindconnect.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import com.mindconnect.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.mindconnect.domain.providermodelai.port.repository.ProviderModelAiRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProviderModelAiUseCasesTest {

    private FakeProviderModelAiRepository repository;
    private RegisterProviderModelAiUseCase register;
    private GetProviderModelAiByIdUseCase getById;
    private ListProviderModelAiUseCase list;
    private UpdateProviderModelAiUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeProviderModelAiRepository();
        register = new RegisterProviderModelAiUseCase(repository);
        getById = new GetProviderModelAiByIdUseCase(repository);
        list = new ListProviderModelAiUseCase(repository);
        update = new UpdateProviderModelAiUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ProviderModelAiResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ProviderModelAiResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ProviderModelAiId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProviderModelAiNotFoundApplicationException.class, () -> getById.execute(ProviderModelAiId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ProviderModelAiResponse created = register.execute(commandA());

        ProviderModelAiResponse updated = update.execute(updateCommand(new ProviderModelAiId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ProviderModelAiId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProviderModelAiNotFoundApplicationException.class, () -> update.execute(updateCommand(ProviderModelAiId.generate())));
    }

    private RegisterProviderModelAiCommand commandA() {
        return new RegisterProviderModelAiCommand("valor-a", "valor-a", "valor-a");
    }

    private UpdateProviderModelAiCommand updateCommand(ProviderModelAiId id) {
        return new UpdateProviderModelAiCommand(id, "valor-b", "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeProviderModelAiRepository implements ProviderModelAiRepository {
        private final Map<ProviderModelAiId, ProviderModelAi> store = new LinkedHashMap<>();

        @Override
        public ProviderModelAi save(ProviderModelAi aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ProviderModelAi> findById(ProviderModelAiId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ProviderModelAi> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ProviderModelAi aggregate) {
            store.remove(aggregate.id());
        }
    }
}
