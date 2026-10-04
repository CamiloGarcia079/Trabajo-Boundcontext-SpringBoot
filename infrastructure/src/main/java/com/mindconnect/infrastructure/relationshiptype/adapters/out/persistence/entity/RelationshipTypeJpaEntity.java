package com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA de la tabla relationship_types. Vive solo en infraestructura: el dominio no la conoce.
 *
 * <p>Las llaves foráneas se guardan como UUID simples (sin @ManyToOne), igual que en el
 * modelo de dominio, que se relaciona con otros agregados solo por id.</p>
 */
@Entity
@Table(name = "relationship_types")
public class RelationshipTypeJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "description", nullable = false, unique = true, length = 50)
    private String description;

    /** JPA exige un constructor sin argumentos. */
    public RelationshipTypeJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
