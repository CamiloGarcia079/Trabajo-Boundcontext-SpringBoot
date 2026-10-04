package com.mindconnect.application.citymunicipality.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.citymunicipality.command.RegisterCityMunicipalityCommand;
import com.mindconnect.application.citymunicipality.command.UpdateCityMunicipalityCommand;
import com.mindconnect.application.citymunicipality.dto.CityMunicipalityResponse;
import com.mindconnect.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.mindconnect.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.mindconnect.domain.citymunicipality.port.repository.CityMunicipalityRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CityMunicipalityUseCasesTest {

    private FakeCityMunicipalityRepository repository;
    private RegisterCityMunicipalityUseCase register;
    private GetCityMunicipalityByIdUseCase getById;
    private ListCityMunicipalityUseCase list;
    private UpdateCityMunicipalityUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeCityMunicipalityRepository();
        register = new RegisterCityMunicipalityUseCase(repository);
        getById = new GetCityMunicipalityByIdUseCase(repository);
        list = new ListCityMunicipalityUseCase(repository);
        update = new UpdateCityMunicipalityUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        CityMunicipalityResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        CityMunicipalityResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new CityMunicipalityId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(CityMunicipalityNotFoundApplicationException.class, () -> getById.execute(CityMunicipalityId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        CityMunicipalityResponse created = register.execute(commandA());

        CityMunicipalityResponse updated = update.execute(updateCommand(new CityMunicipalityId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new CityMunicipalityId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(CityMunicipalityNotFoundApplicationException.class, () -> update.execute(updateCommand(CityMunicipalityId.generate())));
    }

    private RegisterCityMunicipalityCommand commandA() {
        return new RegisterCityMunicipalityCommand("valor-a", "valor-a", "valor-a", UUID.randomUUID());
    }

    private UpdateCityMunicipalityCommand updateCommand(CityMunicipalityId id) {
        return new UpdateCityMunicipalityCommand(id, "valor-b", "valor-b", "valor-b", UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeCityMunicipalityRepository implements CityMunicipalityRepository {
        private final Map<CityMunicipalityId, CityMunicipality> store = new LinkedHashMap<>();

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
            store.remove(aggregate.id());
        }
    }
}
