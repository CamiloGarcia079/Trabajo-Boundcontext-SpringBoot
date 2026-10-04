package com.mindconnect.application.gender.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.gender.command.RegisterGenderCommand;
import com.mindconnect.application.gender.command.UpdateGenderCommand;
import com.mindconnect.application.gender.dto.GenderResponse;
import com.mindconnect.application.gender.exception.GenderNotFoundApplicationException;
import com.mindconnect.domain.gender.model.aggregate.Gender;
import com.mindconnect.domain.gender.model.valueobject.GenderId;
import com.mindconnect.domain.gender.port.repository.GenderRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenderUseCasesTest {

    private FakeGenderRepository repository;
    private RegisterGenderUseCase register;
    private GetGenderByIdUseCase getById;
    private ListGenderUseCase list;
    private UpdateGenderUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeGenderRepository();
        register = new RegisterGenderUseCase(repository);
        getById = new GetGenderByIdUseCase(repository);
        list = new ListGenderUseCase(repository);
        update = new UpdateGenderUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        GenderResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        GenderResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new GenderId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(GenderNotFoundApplicationException.class, () -> getById.execute(GenderId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        GenderResponse created = register.execute(commandA());

        GenderResponse updated = update.execute(updateCommand(new GenderId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new GenderId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(GenderNotFoundApplicationException.class, () -> update.execute(updateCommand(GenderId.generate())));
    }

    private RegisterGenderCommand commandA() {
        return new RegisterGenderCommand("valor-a");
    }

    private UpdateGenderCommand updateCommand(GenderId id) {
        return new UpdateGenderCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeGenderRepository implements GenderRepository {
        private final Map<GenderId, Gender> store = new LinkedHashMap<>();

        @Override
        public Gender save(Gender aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Gender> findById(GenderId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Gender> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Gender aggregate) {
            store.remove(aggregate.id());
        }
    }
}
