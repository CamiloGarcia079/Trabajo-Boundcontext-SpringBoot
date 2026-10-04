package com.mindconnect.application.professionalstudy.usecase;

import com.mindconnect.application.professionalstudy.command.RegisterProfessionalStudyCommand;
import com.mindconnect.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.mindconnect.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class RegisterProfessionalStudyUseCase {

    private final ProfessionalStudyRepository repository;

    public RegisterProfessionalStudyUseCase(ProfessionalStudyRepository repository) {
        this.repository = repository;
    }

    public ProfessionalStudyResponse execute(RegisterProfessionalStudyCommand command) {
        ProfessionalStudy aggregate = ProfessionalStudy.register(
                command.studyId(), command.professionalId(), command.title(), command.university(), command.isValid(), command.resolutionNumber(), command.countryId());
        ProfessionalStudy saved = repository.save(aggregate);
        return ProfessionalStudyResponse.fromDomain(saved);
    }
}
