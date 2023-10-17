package com.marnikkamil.store.security.subject.infrastructure;

import com.marnikkamil.store.security.configuration.RegistrationRequest;
import com.marnikkamil.store.security.subject.domain.SubjectFacade;
import com.marnikkamil.store.security.subject.dto.SubjectDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
class AuthController {

  SubjectFacade subjectFacade;

  @Autowired
  AuthController(@Autowired SubjectFacade subjectFacade) {
    this.subjectFacade = subjectFacade;
  }

  @PostMapping("/register")
  public ResponseEntity<SubjectDto> registerUser(@RequestBody RegistrationRequest subject) {
    SubjectDto registeredUser = subjectFacade.registerSubject(subject);

    return ResponseEntity.ok(registeredUser);
  }

}
