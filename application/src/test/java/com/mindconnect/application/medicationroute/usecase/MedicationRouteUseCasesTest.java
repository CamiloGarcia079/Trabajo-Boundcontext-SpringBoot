package com.mindconnect.application.medicationroute.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.medicationroute.command.RegisterMedicationRouteCommand;
import com.mindconnect.application.medicationroute.command.UpdateMedicationRouteCommand;
import com.mindconnect.application.medicationroute.dto.MedicationRouteResponse;
import com.mindconnect.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import com.mindconnect.domain.medicationroute.model.aggregate.MedicationRoute;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.mindconnect.domain.medicationroute.port.repository.MedicationRouteRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedicationRouteUseCasesTest {

    private FakeMedicationRouteRepository repository;
    private RegisterMedicationRouteUseCase register;
    private GetMedicationRouteByIdUseCase getById;
    private ListMedicationRouteUseCase list;
    private UpdateMedicationRouteUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeMedicationRouteRepository();
        register = new RegisterMedicationRouteUseCase(repository);
        getById = new GetMedicationRouteByIdUseCase(repository);
        list = new ListMedicationRouteUseCase(repository);
        update = new UpdateMedicationRouteUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        MedicationRouteResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        MedicationRouteResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new MedicationRouteId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(MedicationRouteNotFoundApplicationException.class, () -> getById.execute(MedicationRouteId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        MedicationRouteResponse created = register.execute(commandA());

        MedicationRouteResponse updated = update.execute(updateCommand(new MedicationRouteId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new MedicationRouteId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(MedicationRouteNotFoundApplicationException.class, () -> update.execute(updateCommand(MedicationRouteId.generate())));
    }

    private RegisterMedicationRouteCommand commandA() {
        return new RegisterMedicationRouteCommand("valor-a", "valor-a");
    }

    private UpdateMedicationRouteCommand updateCommand(MedicationRouteId id) {
        return new UpdateMedicationRouteCommand(id, "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeMedicationRouteRepository implements MedicationRouteRepository {
        private final Map<MedicationRouteId, MedicationRoute> store = new LinkedHashMap<>();

        @Override
        public MedicationRoute save(MedicationRoute aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<MedicationRoute> findById(MedicationRouteId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<MedicationRoute> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(MedicationRoute aggregate) {
            store.remove(aggregate.id());
        }
    }
}
