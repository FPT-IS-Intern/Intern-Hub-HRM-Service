package com.fis.hrmservice.infra.persistence.config;

import com.fis.hrmservice.domain.port.input.RegisterUserUseCase;
import com.fis.hrmservice.domain.port.output.IdGeneratorPort;
import com.fis.hrmservice.domain.port.output.PositionRepositoryPort;
import com.fis.hrmservice.domain.port.output.UserRepositoryPort;
import com.fis.hrmservice.domain.service.UserValidationService;
import com.fis.hrmservice.domain.usecase.RegisterUserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for domain beans.
 * This is where we wire up the use cases with their dependencies.
 */
@Configuration
public class DomainBeanConfig {

    @Bean
    public UserValidationService userValidationService() {
        return new UserValidationService();
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(
            UserRepositoryPort userRepository,
            PositionRepositoryPort positionRepository,
            IdGeneratorPort idGenerator,
            UserValidationService validationService) {
        return new RegisterUserUseCaseImpl(
                userRepository,
                positionRepository,
                idGenerator,
                validationService
        );
    }
}
