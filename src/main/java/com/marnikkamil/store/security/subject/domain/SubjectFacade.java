package com.marnikkamil.store.security.subject.domain;

import com.marnikkamil.store.security.configuration.RegistrationRequest;
import com.marnikkamil.store.security.subject.dto.SubjectDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubjectFacade {

  PasswordEncoder passwordEncoder;
  SubjectRepository subjectRepository;

  public SubjectDto registerSubject(RegistrationRequest registrationRequest) {
    final Subject toSave = Subject.builder()
        .username(registrationRequest.getUsername())
        .password(passwordEncoder.encode(registrationRequest.getPassword()))
        .role(Subject.Role.from(registrationRequest.getRole()))
        .build();

    return subjectRepository.save(toSave).dto();
  }

}
