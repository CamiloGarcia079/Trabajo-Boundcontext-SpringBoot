package com.mindconnect.application.mentalstatusexam.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.mentalstatusexam.command.RegisterMentalStatusExamCommand;
import com.mindconnect.application.mentalstatusexam.command.UpdateMentalStatusExamCommand;
import com.mindconnect.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.mindconnect.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import com.mindconnect.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.mindconnect.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MentalStatusExamUseCasesTest {

    private FakeMentalStatusExamRepository repository;
    private RegisterMentalStatusExamUseCase register;
    private GetMentalStatusExamByIdUseCase getById;
    private ListMentalStatusExamUseCase list;
    private UpdateMentalStatusExamUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeMentalStatusExamRepository();
        register = new RegisterMentalStatusExamUseCase(repository);
        getById = new GetMentalStatusExamByIdUseCase(repository);
        list = new ListMentalStatusExamUseCase(repository);
        update = new UpdateMentalStatusExamUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        MentalStatusExamResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        MentalStatusExamResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new MentalStatusExamId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(MentalStatusExamNotFoundApplicationException.class, () -> getById.execute(MentalStatusExamId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        MentalStatusExamResponse created = register.execute(commandA());

        MentalStatusExamResponse updated = update.execute(updateCommand(new MentalStatusExamId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new MentalStatusExamId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(MentalStatusExamNotFoundApplicationException.class, () -> update.execute(updateCommand(MentalStatusExamId.generate())));
    }

    private RegisterMentalStatusExamCommand commandA() {
        return new RegisterMentalStatusExamCommand(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", UUID.randomUUID());
    }

    private UpdateMentalStatusExamCommand updateCommand(MentalStatusExamId id) {
        return new UpdateMentalStatusExamCommand(id, UUID.randomUUID(), "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeMentalStatusExamRepository implements MentalStatusExamRepository {
        private final Map<MentalStatusExamId, MentalStatusExam> store = new LinkedHashMap<>();

        @Override
        public MentalStatusExam save(MentalStatusExam aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<MentalStatusExam> findById(MentalStatusExamId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<MentalStatusExam> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(MentalStatusExam aggregate) {
            store.remove(aggregate.id());
        }
    }
}
