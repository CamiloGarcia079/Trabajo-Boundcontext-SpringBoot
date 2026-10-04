package com.mindconnect.application.professional.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.professional.command.RegisterProfessionalCommand;
import com.mindconnect.application.professional.command.UpdateProfessionalCommand;
import com.mindconnect.application.professional.dto.ProfessionalResponse;
import com.mindconnect.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.mindconnect.domain.professional.model.aggregate.Professional;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;
import com.mindconnect.domain.professional.port.repository.ProfessionalRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProfessionalUseCasesTest {

    private FakeProfessionalRepository repository;
    private RegisterProfessionalUseCase register;
    private GetProfessionalByIdUseCase getById;
    private ListProfessionalUseCase list;
    private UpdateProfessionalUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeProfessionalRepository();
        register = new RegisterProfessionalUseCase(repository);
        getById = new GetProfessionalByIdUseCase(repository);
        list = new ListProfessionalUseCase(repository);
        update = new UpdateProfessionalUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ProfessionalResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ProfessionalResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ProfessionalId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalNotFoundApplicationException.class, () -> getById.execute(ProfessionalId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ProfessionalResponse created = register.execute(commandA());

        ProfessionalResponse updated = update.execute(updateCommand(new ProfessionalId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ProfessionalId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalNotFoundApplicationException.class, () -> update.execute(updateCommand(ProfessionalId.generate())));
    }

    private RegisterProfessionalCommand commandA() {
        return new RegisterProfessionalCommand(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), "valor-a", UUID.randomUUID());
    }

    private UpdateProfessionalCommand updateCommand(ProfessionalId id) {
        return new UpdateProfessionalCommand(id, UUID.randomUUID(), "valor-b", "valor-b", "valor-b", UUID.randomUUID(), "valor-b", UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeProfessionalRepository implements ProfessionalRepository {
        private final Map<ProfessionalId, Professional> store = new LinkedHashMap<>();

        @Override
        public Professional save(Professional aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Professional> findById(ProfessionalId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Professional> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Professional aggregate) {
            store.remove(aggregate.id());
        }
    }
}
