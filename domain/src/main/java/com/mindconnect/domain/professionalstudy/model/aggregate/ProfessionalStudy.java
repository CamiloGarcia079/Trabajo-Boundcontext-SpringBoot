package com.mindconnect.domain.professionalstudy.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.professionalstudy.event.ProfessionalStudyRegisteredEvent;
import com.mindconnect.domain.professionalstudy.event.ProfessionalStudyUpdatedEvent;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

/**
 * Agregado raíz del contexto professionalstudy: representa la tabla professional_studies.
 *
 * <p>Relaciones (se guardan solo por id, no como objetos):</p>
 * <ul>
 *   <li>studyId -> Study</li>
 *   <li>professionalId -> Professional</li>
 *   <li>countryId -> Country</li>
 * </ul>
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ProfessionalStudy extends AggregateRoot {

    private final ProfessionalStudyId id;
    private UUID studyId;
    private UUID professionalId;
    private String title;
    private String university;
    private boolean isValid;
    private String resolutionNumber;
    private UUID countryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProfessionalStudy(
            ProfessionalStudyId id,
            UUID studyId,
            UUID professionalId,
            String title,
            String university,
            boolean isValid,
            String resolutionNumber,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(studyId, "studyId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(title, "title");
        DomainValidations.required(university, "university");
        DomainValidations.required(countryId, "countryId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.studyId = studyId;
        this.professionalId = professionalId;
        this.title = title;
        this.university = university;
        this.isValid = isValid;
        this.resolutionNumber = resolutionNumber;
        this.countryId = countryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ProfessionalStudy register(
            UUID studyId,
            UUID professionalId,
            String title,
            String university,
            boolean isValid,
            String resolutionNumber,
            UUID countryId) {
        ProfessionalStudyId id = ProfessionalStudyId.generate();
        LocalDateTime now = LocalDateTime.now();
        ProfessionalStudy aggregate = new ProfessionalStudy(id, studyId, professionalId, title, university, isValid, resolutionNumber, countryId, now, now);
        aggregate.recordEvent(new ProfessionalStudyRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ProfessionalStudy restore(
            ProfessionalStudyId id,
            UUID studyId,
            UUID professionalId,
            String title,
            String university,
            boolean isValid,
            String resolutionNumber,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ProfessionalStudy(id, studyId, professionalId, title, university, isValid, resolutionNumber, countryId, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            UUID studyId,
            UUID professionalId,
            String title,
            String university,
            boolean isValid,
            String resolutionNumber,
            UUID countryId) {
        DomainValidations.required(studyId, "studyId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(title, "title");
        DomainValidations.required(university, "university");
        DomainValidations.required(countryId, "countryId");
        this.studyId = studyId;
        this.professionalId = professionalId;
        this.title = title;
        this.university = university;
        this.isValid = isValid;
        this.resolutionNumber = resolutionNumber;
        this.countryId = countryId;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ProfessionalStudyUpdatedEvent(this.id, now));
    }

    public ProfessionalStudyId id() { return id; }
    public UUID studyId() { return studyId; }
    public UUID professionalId() { return professionalId; }
    public String title() { return title; }
    public String university() { return university; }
    public boolean isValid() { return isValid; }
    public String resolutionNumber() { return resolutionNumber; }
    public UUID countryId() { return countryId; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
