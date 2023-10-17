package com.marnikkamil.store.security.subject.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SubjectConfiguration {

  @Bean
  public SubjectFacade createUserFacade(PasswordEncoder passwordEncoder, SubjectRepository subjectRepository) {
    return SubjectFacade.builder()
        .passwordEncoder(passwordEncoder)
        .subjectRepository(subjectRepository)
        .build();
  }

}
