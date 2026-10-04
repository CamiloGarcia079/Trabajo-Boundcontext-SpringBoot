package com.mindconnect.application.professionaltype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.professionaltype.command.RegisterProfessionalTypeCommand;
import com.mindconnect.application.professionaltype.command.UpdateProfessionalTypeCommand;
import com.mindconnect.application.professionaltype.dto.ProfessionalTypeResponse;
import com.mindconnect.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import com.mindconnect.domain.professionaltype.model.aggregate.ProfessionalType;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.mindconnect.domain.professionaltype.port.repository.ProfessionalTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProfessionalTypeUseCasesTest {

    private FakeProfessionalTypeRepository repository;
    private RegisterProfessionalTypeUseCase register;
    private GetProfessionalTypeByIdUseCase getById;
    private ListProfessionalTypeUseCase list;
    private UpdateProfessionalTypeUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeProfessionalTypeRepository();
        register = new RegisterProfessionalTypeUseCase(repository);
        getById = new GetProfessionalTypeByIdUseCase(repository);
        list = new ListProfessionalTypeUseCase(repository);
        update = new UpdateProfessionalTypeUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ProfessionalTypeResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ProfessionalTypeResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ProfessionalTypeId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalTypeNotFoundApplicationException.class, () -> getById.execute(ProfessionalTypeId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ProfessionalTypeResponse created = register.execute(commandA());

        ProfessionalTypeResponse updated = update.execute(updateCommand(new ProfessionalTypeId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ProfessionalTypeId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalTypeNotFoundApplicationException.class, () -> update.execute(updateCommand(ProfessionalTypeId.generate())));
    }

    private RegisterProfessionalTypeCommand commandA() {
        return new RegisterProfessionalTypeCommand("valor-a");
    }

    private UpdateProfessionalTypeCommand updateCommand(ProfessionalTypeId id) {
        return new UpdateProfessionalTypeCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeProfessionalTypeRepository implements ProfessionalTypeRepository {
        private final Map<ProfessionalTypeId, ProfessionalType> store = new LinkedHashMap<>();

        @Override
        public ProfessionalType save(ProfessionalType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ProfessionalType> findById(ProfessionalTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ProfessionalType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ProfessionalType aggregate) {
            store.remove(aggregate.id());
        }
    }
}
