package com.marnikkamil.store.security.subject.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class SubjectDto {

  public enum Role {
    ADMIN,
    CUSTOMER
  }

  String userId;
  String username;
  Role role;

}
