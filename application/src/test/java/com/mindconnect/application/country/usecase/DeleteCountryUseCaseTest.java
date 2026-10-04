package com.mindconnect.application.country.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.country.exception.CountryNotFoundApplicationException;
import com.mindconnect.domain.country.event.CountryDeletedEvent;
import com.mindconnect.domain.country.model.aggregate.Country;
import com.mindconnect.domain.country.model.valueobject.CountryId;
import com.mindconnect.domain.country.port.repository.CountryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteCountryUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        Country aggregate = Country.register("valor-a", "valor-a", "valor-a", "valor-a");
        FakeCountryRepository repository = new FakeCountryRepository();
        repository.save(aggregate);
        DeleteCountryUseCase useCase = new DeleteCountryUseCase(repository);

        CountryDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeCountryRepository repository = new FakeCountryRepository();
        DeleteCountryUseCase useCase = new DeleteCountryUseCase(repository);

        assertThrows(CountryNotFoundApplicationException.class, () -> useCase.execute(CountryId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeCountryRepository implements CountryRepository {
        private final Map<CountryId, Country> store = new LinkedHashMap<>();
        private Country deleted;

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
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
