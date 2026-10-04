package com.mindconnect.infrastructure.phonecontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.phonecontact.usecase.DeletePhoneContactUseCase;
import com.mindconnect.application.phonecontact.usecase.GetPhoneContactByIdUseCase;
import com.mindconnect.application.phonecontact.usecase.ListPhoneContactUseCase;
import com.mindconnect.application.phonecontact.usecase.RegisterPhoneContactUseCase;
import com.mindconnect.application.phonecontact.usecase.UpdatePhoneContactUseCase;
import com.mindconnect.domain.phonecontact.port.repository.PhoneContactRepository;
import com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.mappers.PhoneContactPersistenceMapper;
import com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.repositories.PhoneContactJpaRepository;
import com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.repositories.PhoneContactRepositoryAdapter;

/**
 * Conecta las piezas del contexto phonecontact: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class PhoneContactBeansConfig {

    @Bean
    public PhoneContactPersistenceMapper phoneContactPersistenceMapper() {
        return new PhoneContactPersistenceMapper();
    }

    @Bean
    public PhoneContactRepository phoneContactRepository(PhoneContactJpaRepository repository, PhoneContactPersistenceMapper mapper) {
        return new PhoneContactRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterPhoneContactUseCase registerPhoneContactUseCase(PhoneContactRepository repository) {
        return new RegisterPhoneContactUseCase(repository);
    }

    @Bean
    public GetPhoneContactByIdUseCase getPhoneContactByIdUseCase(PhoneContactRepository repository) {
        return new GetPhoneContactByIdUseCase(repository);
    }

    @Bean
    public ListPhoneContactUseCase listPhoneContactUseCase(PhoneContactRepository repository) {
        return new ListPhoneContactUseCase(repository);
    }

    @Bean
    public UpdatePhoneContactUseCase updatePhoneContactUseCase(PhoneContactRepository repository) {
        return new UpdatePhoneContactUseCase(repository);
    }

    @Bean
    public DeletePhoneContactUseCase deletePhoneContactUseCase(PhoneContactRepository repository) {
        return new DeletePhoneContactUseCase(repository);
    }
}
