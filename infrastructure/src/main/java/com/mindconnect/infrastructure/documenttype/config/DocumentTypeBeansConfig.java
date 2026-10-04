package com.mindconnect.infrastructure.documenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.documenttype.usecase.DeleteDocumentTypeUseCase;
import com.mindconnect.application.documenttype.usecase.GetDocumentTypeByIdUseCase;
import com.mindconnect.application.documenttype.usecase.ListDocumentTypeUseCase;
import com.mindconnect.application.documenttype.usecase.RegisterDocumentTypeUseCase;
import com.mindconnect.application.documenttype.usecase.UpdateDocumentTypeUseCase;
import com.mindconnect.domain.documenttype.port.repository.DocumentTypeRepository;
import com.mindconnect.infrastructure.documenttype.adapters.out.persistence.mappers.DocumentTypePersistenceMapper;
import com.mindconnect.infrastructure.documenttype.adapters.out.persistence.repositories.DocumentTypeJpaRepository;
import com.mindconnect.infrastructure.documenttype.adapters.out.persistence.repositories.DocumentTypeRepositoryAdapter;

/**
 * Conecta las piezas del contexto documenttype: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class DocumentTypeBeansConfig {

    @Bean
    public DocumentTypePersistenceMapper documentTypePersistenceMapper() {
        return new DocumentTypePersistenceMapper();
    }

    @Bean
    public DocumentTypeRepository documentTypeRepository(DocumentTypeJpaRepository repository, DocumentTypePersistenceMapper mapper) {
        return new DocumentTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterDocumentTypeUseCase registerDocumentTypeUseCase(DocumentTypeRepository repository) {
        return new RegisterDocumentTypeUseCase(repository);
    }

    @Bean
    public GetDocumentTypeByIdUseCase getDocumentTypeByIdUseCase(DocumentTypeRepository repository) {
        return new GetDocumentTypeByIdUseCase(repository);
    }

    @Bean
    public ListDocumentTypeUseCase listDocumentTypeUseCase(DocumentTypeRepository repository) {
        return new ListDocumentTypeUseCase(repository);
    }

    @Bean
    public UpdateDocumentTypeUseCase updateDocumentTypeUseCase(DocumentTypeRepository repository) {
        return new UpdateDocumentTypeUseCase(repository);
    }

    @Bean
    public DeleteDocumentTypeUseCase deleteDocumentTypeUseCase(DocumentTypeRepository repository) {
        return new DeleteDocumentTypeUseCase(repository);
    }
}
