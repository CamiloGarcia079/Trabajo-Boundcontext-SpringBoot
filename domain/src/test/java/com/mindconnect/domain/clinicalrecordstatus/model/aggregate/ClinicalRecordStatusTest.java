package com.mindconnect.domain.clinicalrecordstatus.model.aggregate;


import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.clinicalrecordstatus.event.ClinicalRecordStatusRegisteredEvent;
import com.mindconnect.domain.clinicalrecordstatus.event.ClinicalRecordStatusUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClinicalRecordStatusTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ClinicalRecordStatus aggregate = ClinicalRecordStatus.register("valor-a", "valor-a");

        assertEquals(1, aggregate.domainEvents().size());
        ClinicalRecordStatusRegisteredEvent event = assertInstanceOf(
                ClinicalRecordStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ClinicalRecordStatus aggregate = ClinicalRecordStatus.register("valor-a", "valor-a");

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ClinicalRecordStatus aggregate = ClinicalRecordStatus.register("valor-a", "valor-a");
        aggregate.clearDomainEvents();

        aggregate.update("valor-b", "valor-b");

        assertEquals("valor-b", aggregate.code());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ClinicalRecordStatusUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ClinicalRecordStatus.register(null, "valor-a"));
    }
}
