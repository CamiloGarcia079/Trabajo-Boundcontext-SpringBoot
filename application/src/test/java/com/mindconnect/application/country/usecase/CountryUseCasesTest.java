package com.mindconnect.application.country.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.country.command.RegisterCountryCommand;
import com.mindconnect.application.country.command.UpdateCountryCommand;
import com.mindconnect.application.country.dto.CountryResponse;
import com.mindconnect.application.country.exception.CountryNotFoundApplicationException;
import com.mindconnect.domain.country.model.aggregate.Country;
import com.mindconnect.domain.country.model.valueobject.CountryId;
import com.mindconnect.domain.country.port.repository.CountryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountryUseCasesTest {

    private FakeCountryRepository repository;
    private RegisterCountryUseCase register;
    private GetCountryByIdUseCase getById;
    private ListCountryUseCase list;
    private UpdateCountryUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeCountryRepository();
        register = new RegisterCountryUseCase(repository);
        getById = new GetCountryByIdUseCase(repository);
        list = new ListCountryUseCase(repository);
        update = new UpdateCountryUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        CountryResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        CountryResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new CountryId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(CountryNotFoundApplicationException.class, () -> getById.execute(CountryId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        CountryResponse created = register.execute(commandA());

        CountryResponse updated = update.execute(updateCommand(new CountryId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new CountryId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(CountryNotFoundApplicationException.class, () -> update.execute(updateCommand(CountryId.generate())));
    }

    private RegisterCountryCommand commandA() {
        return new RegisterCountryCommand("valor-a", "valor-a", "valor-a", "valor-a");
    }

    private UpdateCountryCommand updateCommand(CountryId id) {
        return new UpdateCountryCommand(id, "valor-b", "valor-b", "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeCountryRepository implements CountryRepository {
        private final Map<CountryId, Country> store = new LinkedHashMap<>();

        @Override
        public Country save(Country aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Country> findById(CountryId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Country> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Country aggregate) {
            store.remove(aggregate.id());
        }
    }
}
