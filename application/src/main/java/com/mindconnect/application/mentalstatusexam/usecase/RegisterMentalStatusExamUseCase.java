package com.mindconnect.application.mentalstatusexam.usecase;

import com.mindconnect.application.mentalstatusexam.command.RegisterMentalStatusExamCommand;
import com.mindconnect.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.mindconnect.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.mindconnect.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class RegisterMentalStatusExamUseCase {

    private final MentalStatusExamRepository repository;

    public RegisterMentalStatusExamUseCase(MentalStatusExamRepository repository) {
        this.repository = repository;
    }

    public MentalStatusExamResponse execute(RegisterMentalStatusExamCommand command) {
        MentalStatusExam aggregate = MentalStatusExam.register(
                command.encounterId(), command.appearance(), command.behavior(), command.attitude(), command.consciousness(), command.orientation(), command.attention(), command.memory(), command.speech(), command.mood(), command.affect(), command.thoughtProcess(), command.thoughtContent(), command.perception(), command.judgment(), command.insight(), command.psychomotorActivity(), command.observations(), command.createdBy());
        MentalStatusExam saved = repository.save(aggregate);
        return MentalStatusExamResponse.fromDomain(saved);
    }
}
