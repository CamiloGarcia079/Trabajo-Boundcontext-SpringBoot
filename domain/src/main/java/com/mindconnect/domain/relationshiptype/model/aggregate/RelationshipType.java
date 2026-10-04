package com.mindconnect.domain.relationshiptype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.relationshiptype.event.RelationshipTypeRegisteredEvent;
import com.mindconnect.domain.relationshiptype.event.RelationshipTypeUpdatedEvent;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;

/**
 * Agregado raíz del contexto relationshiptype: representa la tabla relationship_types.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class RelationshipType extends AggregateRoot {

    private final RelationshipTypeId id;
    private String description;

    private RelationshipType(
            RelationshipTypeId id,
            String description) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(description, "description");
        this.description = description;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static RelationshipType register(
            String description) {
        RelationshipTypeId id = RelationshipTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        RelationshipType aggregate = new RelationshipType(id, description);
        aggregate.recordEvent(new RelationshipTypeRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static RelationshipType restore(
            RelationshipTypeId id,
            String description) {
        return new RelationshipType(id, description);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String description) {
        DomainValidations.required(description, "description");
        this.description = description;
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new RelationshipTypeUpdatedEvent(this.id, now));
    }

    public RelationshipTypeId id() { return id; }
    public String description() { return description; }
}
