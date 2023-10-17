package com.marnikkamil.store.samples

import com.marnikkamil.store.security.subject.dto.SubjectDto
import groovy.transform.CompileStatic

trait SampleSubjects {

  static final SubjectDto ADMIN = createSubjectDto("5f4f7d9f9572491e648c946b", "Admin", SubjectDto.Role.ADMIN)
  static final SubjectDto JOHN = createSubjectDto("601eaeaf9c6d500012340001", "John", SubjectDto.Role.CUSTOMER)
  static final SubjectDto KEVIN = createSubjectDto("60a1e75b5f1915000156c28f", "Kevin", SubjectDto.Role.CUSTOMER)
  static final SubjectDto MARC = createSubjectDto("615f6d2b0e5b5a0015067ae5", "Marc", SubjectDto.Role.CUSTOMER)

  private static SubjectDto createSubjectDto(String id, String name, SubjectDto.Role role) {
    SubjectDto.builder()
        .userId(id)
        .username(name)
        .role(role)
        .build()
  }

}