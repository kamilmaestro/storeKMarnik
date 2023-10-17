package com.marnikkamil.store.security.subject.domain;

import com.marnikkamil.store.security.subject.dto.SubjectDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("subject")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Subject {

  enum Role {
    ADMIN,
    CUSTOMER;

    static Role from(String value) {
      try {
        return Role.valueOf(value);
      } catch (Exception e) {
        return CUSTOMER;
      }
    }
  }

  @MongoId
  ObjectId id;
  String username;
  String password;
  Role role = Role.CUSTOMER;

  SubjectDto dto() {
    return SubjectDto.builder()
        .userId(this.id.toString())
        .username(this.username)
        .role(SubjectDto.Role.valueOf(this.role.toString()))
        .build();
  }

}
