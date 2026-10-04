package com.mindconnect.application.professionalstudy.usecase;

import com.mindconnect.application.professionalstudy.command.UpdateProfessionalStudyCommand;
import com.mindconnect.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.mindconnect.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import com.mindconnect.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class UpdateProfessionalStudyUseCase {

    private final ProfessionalStudyRepository repository;

    public UpdateProfessionalStudyUseCase(ProfessionalStudyRepository repository) {
        this.repository = repository;
    }

    public ProfessionalStudyResponse execute(UpdateProfessionalStudyCommand command) {
        ProfessionalStudy aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ProfessionalStudyNotFoundApplicationException(command.id()));
        aggregate.update(
                command.studyId(), command.professionalId(), command.title(), command.university(), command.isValid(), command.resolutionNumber(), command.countryId());
        ProfessionalStudy saved = repository.save(aggregate);
        return ProfessionalStudyResponse.fromDomain(saved);
    }
}
