package com.mindconnect.application.citymunicipality.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.mindconnect.domain.citymunicipality.event.CityMunicipalityDeletedEvent;
import com.mindconnect.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.mindconnect.domain.citymunicipality.port.repository.CityMunicipalityRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteCityMunicipalityUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        CityMunicipality aggregate = CityMunicipality.register("valor-a", "valor-a", "valor-a", UUID.randomUUID());
        FakeCityMunicipalityRepository repository = new FakeCityMunicipalityRepository();
        repository.save(aggregate);
        DeleteCityMunicipalityUseCase useCase = new DeleteCityMunicipalityUseCase(repository);

        CityMunicipalityDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeCityMunicipalityRepository repository = new FakeCityMunicipalityRepository();
        DeleteCityMunicipalityUseCase useCase = new DeleteCityMunicipalityUseCase(repository);

        assertThrows(CityMunicipalityNotFoundApplicationException.class, () -> useCase.execute(CityMunicipalityId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeCityMunicipalityRepository implements CityMunicipalityRepository {
        private final Map<CityMunicipalityId, CityMunicipality> store = new LinkedHashMap<>();
        private CityMunicipality deleted;

        @Override
        public CityMunicipality save(CityMunicipality aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<CityMunicipality> findById(CityMunicipalityId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<CityMunicipality> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(CityMunicipality aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
