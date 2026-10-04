package com.mindconnect.infrastructure.emailcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.emailcontact.usecase.DeleteEmailContactUseCase;
import com.mindconnect.application.emailcontact.usecase.GetEmailContactByIdUseCase;
import com.mindconnect.application.emailcontact.usecase.ListEmailContactUseCase;
import com.mindconnect.application.emailcontact.usecase.RegisterEmailContactUseCase;
import com.mindconnect.application.emailcontact.usecase.UpdateEmailContactUseCase;
import com.mindconnect.domain.emailcontact.port.repository.EmailContactRepository;
import com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.mappers.EmailContactPersistenceMapper;
import com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.repositories.EmailContactJpaRepository;
import com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.repositories.EmailContactRepositoryAdapter;

/**
 * Conecta las piezas del contexto emailcontact: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class EmailContactBeansConfig {

    @Bean
    public EmailContactPersistenceMapper emailContactPersistenceMapper() {
        return new EmailContactPersistenceMapper();
    }

    @Bean
    public EmailContactRepository emailContactRepository(EmailContactJpaRepository repository, EmailContactPersistenceMapper mapper) {
        return new EmailContactRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterEmailContactUseCase registerEmailContactUseCase(EmailContactRepository repository) {
        return new RegisterEmailContactUseCase(repository);
    }

    @Bean
    public GetEmailContactByIdUseCase getEmailContactByIdUseCase(EmailContactRepository repository) {
        return new GetEmailContactByIdUseCase(repository);
    }

    @Bean
    public ListEmailContactUseCase listEmailContactUseCase(EmailContactRepository repository) {
        return new ListEmailContactUseCase(repository);
    }

    @Bean
    public UpdateEmailContactUseCase updateEmailContactUseCase(EmailContactRepository repository) {
        return new UpdateEmailContactUseCase(repository);
    }

    @Bean
    public DeleteEmailContactUseCase deleteEmailContactUseCase(EmailContactRepository repository) {
        return new DeleteEmailContactUseCase(repository);
    }
}
